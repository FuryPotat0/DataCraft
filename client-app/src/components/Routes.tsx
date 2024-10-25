import { Route, Routes } from "react-router-dom"
import { HomePage } from "../pages/HomePage"
import Navbar from "./Navbar"
import { DownloadPage } from "../pages/DownloadPage"
import { ConstructorPage } from "../pages/ConstructorPage"

export const AppRoutes: React.FunctionComponent = () => {
    return (
        <div>
            <Navbar />
            <Routes>
                <Route
                    path="/"
                    element={<ConstructorPage />}
                />
                <Route
                    path="/import"
                    element={<DownloadPage />}
                />
            </Routes>
        </div>
    )
}