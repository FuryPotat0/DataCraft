import { AppBar, Box, Button, Toolbar, Typography } from '@mui/material';
import React from 'react';
import { useNavigate } from 'react-router-dom';

export const Navbar: React.FunctionComponent = () => {
    const navigate = useNavigate()

    return (
        <Box sx={{ flexGrow: 1 }}>
            <AppBar position="static">
                <Toolbar variant="dense">
                    <Typography variant="h4" component="h1">
                        DataCraft
                    </Typography>
                    <Button color="inherit" sx={{ marginLeft: '1em' }} onClick={() => navigate('/')}>Home</Button>
                    <Button color="inherit" onClick={() => navigate('/import')}>Import</Button>
                </Toolbar>
            </AppBar>
        </Box>
    );
};

export default Navbar;