import { Button, styled } from '@mui/material';
import React, { useState } from 'react';
import CloudUploadIcon from '@mui/icons-material/CloudUpload';
import { toast } from 'react-toastify';
import { useHttp } from '../../hooks/http.hook';

const VisuallyHiddenInput = styled('input')({
    clip: 'rect(0 0 0 0)',
    clipPath: 'inset(50%)',
    height: 1,
    overflow: 'hidden',
    position: 'absolute',
    bottom: 0,
    left: 0,
    whiteSpace: 'nowrap',
    width: 1,
});

type DataFileAddRequest = {
    dataTypeId: string
    file: File
}

export const ImportFile: React.FunctionComponent = () => {
    const { request } = useHttp()
    //const [selectedFile, setSelectedFile] = useState<File | null>(null);

    /*const handleFileChange = (event: React.ChangeEvent<HTMLInputElement>) => {
        if (event.target.files !== null) {
            setSelectedFile(event.target.files[0])
        }
    };*/

    /*const readFileContents = (file: File) => {
        const reader = new FileReader()
        reader.onload = (event) => {
            if (event.target !== null) {
                const fileContents = event.target.result as string
                sendFileToServer(fileContents)
            }
        }
        reader.readAsText(file)
    }*/

    const sendFileToServer = async (file: File, fileType: string) => {
        // send to server
        try {
            let requestData: DataFileAddRequest = {
                dataTypeId: fileType,
                file: file
            }

            await request("/data-file/add", "POST", {
                request: requestData
            }, [{ name: 'Content-Type', value: 'applicetion/json' }])
        } catch (err: any) {
            toast.error(err)
        }
    }

    const handleFileUpload = (event: React.ChangeEvent<HTMLInputElement>) => {
        if (event.target.files !== null) {
            //setSelectedFile(event.target.files[0])
            const selectedFile = event.target.files[0]
            const fileExtension = selectedFile.name.split('.').pop()
            if (fileExtension !== 'csv' && fileExtension !== 'json') {
                toast.error('Неверный тип файла');
                return;
            }

            sendFileToServer(selectedFile, fileExtension)

            //readFileContents(selectedFile)
        }
    }

    return (
        <>
            <Button
                component="label"
                role={undefined}
                variant="contained"
                tabIndex={-1}
                startIcon={<CloudUploadIcon />}>
                Upload files
                <VisuallyHiddenInput
                    type="file"
                    onChange={handleFileUpload}
                    multiple
                />
            </Button>
        </>
    );
};