import { Button, Grid, Typography } from "@mui/material";
import { DataGrid } from "@mui/x-data-grid";
import { useState } from "react";

const columnsData = [
    { id: 1, field: 'id', headerName: 'ID', width: 70 },
    { id: 2, field: 'name', headerName: 'Name', width: 200 },
    { id: 3, field: 'age', headerName: 'Age', width: 100 },
]

const rowsData = [
    { id: 1, name: 'John Doe', age: 25 },
    { id: 2, name: 'Jane Doe', age: 30 },
    { id: 3, name: 'Bob Smith', age: 35 },
]

export const ConstructorPage: React.FunctionComponent = () => {
    const [data, setData] = useState(rowsData)
    const [columns, setColumns] = useState(columnsData)

    const handleAddRow = () => {
        const newRow = {
            id: data.length + 1,
            name: `New Row ${data.length}`,
            age: 25,
        };
        setData([...data, newRow]);
    };

    const handleDeleteRow = (rowId: number) => {
        setData(data.filter((row) => row.id !== rowId));
    };

    const handleAddColumn = () => {
        const newColumn = {
            id: columns.length + 1,
            field: 'newColumn',
            headerName: 'New Column',
            width: 200,
        };
        setColumns([...columns, newColumn]);
    };

    const handleDeleteColumn = (columnId: number) => {
        setColumns(columns.filter((column) => column.id !== columnId));
    };

    return (
        <Grid container spacing={2}>
            <Grid item xs={12}>
                <Typography variant="h4">Dashboard</Typography>
            </Grid>
            <Grid item xs={12}>
                <DataGrid
                    rows={data}
                    columns={columns}
                    //pageSize={5}
                    checkboxSelection
                    onRowClick={(params) => console.log(params)}
                    onColumnHeaderClick={(params) => console.log(params)}
                />
            </Grid>
            <Grid item xs={12}>
                <Button variant="contained" color="primary" onClick={handleAddRow}>
                    Add Row
                </Button>
                <Button variant="contained" color="secondary" onClick={() => handleDeleteRow(1)}>
                    Delete Row
                </Button>
                <Button variant="contained" color="primary" onClick={handleAddColumn}>
                    Add Column
                </Button>
                <Button variant="contained" color="secondary" onClick={() => handleDeleteColumn(1)}>
                    Delete Column
                </Button>
            </Grid>
        </Grid>
    );
}