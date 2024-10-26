import { Button, Grid, Paper, Table, TableBody, TableCell, TableContainer, TableHead, TableRow, Typography } from "@mui/material";
import { DataGrid } from "@mui/x-data-grid";
import { useState } from "react";

interface Row {
    id: number;
    cells: { [key: string]: number | string }
}

interface Column {
    id: string;
    label: string;
}

export const ConstructorPage: React.FunctionComponent = () => {
    const [rows, setRows] = useState<Row[]>([
        { id: 1, cells: { A: 10, B: 20, C: 30 } },
        { id: 2, cells: { A: 40, B: 50, C: 60 } },
    ]);

    const [columns, setColumns] = useState<Column[]>([
        { id: 'A', label: 'Column A' },
        { id: 'B', label: 'Column B' },
        { id: 'C', label: 'Column C' },
    ]);

    const [selectedCells, setSelectedCells] = useState<string[]>([]);

    const handleCellChange = (rowId: number, columnId: string, value: number | string) => {
        const updatedRows = rows.map((row) => {
            if (row.id === rowId) {
                return { ...row, cells: { ...row.cells, [columnId]: value } }
            }
            return row
        });
        setRows(updatedRows)
    };

    const handleAddRow = () => {
        const newRow: Row = { id: rows.length + 1, cells: {} as Record<string, number> }
        columns.forEach((column) => {
            newRow.cells[column.id] = 0
        });
        setRows([...rows, newRow])
    };

    const handleDeleteRow = (rowId: number) => {
        const updatedRows = rows.filter((row) => row.id !== rowId)
        setRows(updatedRows)
    };

    const handleAddColumn = () => {
        const newColumn = { id: `D`, label: `Column D` }
        setColumns([...columns, newColumn])
        rows.forEach((row) => {
            row.cells[newColumn.id] = ''
        });
    };

    const handleDeleteColumn = (columnId: string) => {
        const updatedColumns = columns.filter((column) => column.id !== columnId)
        setColumns(updatedColumns)
        const updatedRows = rows.map((row) => {
            const updatedCells = { ...row.cells }
            delete updatedCells[columnId]
            return { ...row, cells: updatedCells }
        })
        setRows(updatedRows)
    }

    const handleSelectCell = (rowId: number, columnId: string) => {
        const cellId = `${rowId}-${columnId}`
        if (selectedCells.includes(cellId)) {
            setSelectedCells(selectedCells.filter((cell) => cell !== cellId))
        } else {
            setSelectedCells([...selectedCells, cellId])
        }
    };

    const handleMathOperation = (operation: string) => {
        const selectedValues = selectedCells.map((cellId) => {
            const [rowId, columnId] = cellId.split('-')
            if (rows !== undefined) {
                return rows.find((row) => row.id === parseInt(rowId, 10))?.cells[columnId] ?? ''
            }
        });

        let result;
        switch (operation) {
            case 'sum':
                result = selectedValues.filter(value => typeof value === 'number').reduce((acc, value) => Number(acc) + Number(value), 0);
                break;
            case 'multiply':
                result = selectedValues.filter(value => typeof value === 'number').reduce((acc, value) => Number(acc) * Number(value), 1);
                break;
            case 'subtract':
                result = selectedValues.filter(value => typeof value === 'number').reduce((acc, value) => Number(acc) - Number(value), 0);
                break;
            case 'divide':
                result = selectedValues.filter(value => typeof value === 'number').reduce((acc, value) => (Number(acc) ?? 1) / Number(value), 1)
                break;
            default:
                throw new Error(`Unsupported operation: ${operation}`);
        }

        alert(`Result: ${result}`);
    }

    return (
        <Grid container spacing={2}>
            <Grid item xs={12}>
                <Typography variant="h4">Dashboard</Typography>
            </Grid>
            <Grid item xs={12}>
                <TableContainer component={Paper}>
                    <Table aria-label="simple table" sx={{ minWidth: '30em', margin: '2em' }}>
                        <TableHead>
                            <TableRow>
                                {columns.map((column) => (
                                    <TableCell key={column.id}>
                                        {column.label}
                                        <button onClick={() => handleDeleteColumn(column.id)}>Delete Column</button>
                                    </TableCell>
                                ))}
                                <TableCell />
                            </TableRow>
                        </TableHead>
                        <TableBody>
                            {rows.map((row) => (
                                <TableRow key={row.id}>
                                    {columns.map((column) => (
                                        <TableCell key={column.id}>
                                            <input
                                                type="number"
                                                value={row.cells[column.id]}
                                                onChange={(event) => handleCellChange(row.id, column.id, event.target.value)}
                                                onClick={() => handleSelectCell(row.id, column.id)}
                                            />
                                        </TableCell>
                                    ))}
                                    <TableCell>
                                        <button onClick={() => handleDeleteRow(row.id)}>Delete Row</button>
                                    </TableCell>
                                </TableRow>
                            ))}
                        </TableBody>
                    </Table>
                    <button onClick={handleAddRow}>Add Row</button>
                    <button onClick={handleAddColumn}>Add Column</button>
                    <button onClick={() => handleMathOperation('sum')}>Sum</button>
                    <button onClick={() => handleMathOperation('multiply')}>Multiply</button>
                    <button onClick={() => handleMathOperation('subtract')}>Subtract</button>
                    <button onClick={() => handleMathOperation('divide')}>Divide</button>
                </TableContainer>
            </Grid>
        </Grid>
    );
}