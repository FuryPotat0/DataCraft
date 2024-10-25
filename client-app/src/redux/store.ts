import { configureStore } from "@reduxjs/toolkit";
import datacraftReducer from './datacraftSlice'

export const store = configureStore({
    reducer: {
        datacraftReducer
    }
})

export type RootState = ReturnType<typeof store.getState>
export type AppDispatch = typeof store.dispatch