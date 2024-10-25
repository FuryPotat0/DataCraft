import { configureStore } from "@reduxjs/toolkit";
import datacraftReducer from './datacraftSlice'
import appReducer from './appSlice'

export const store = configureStore({
    reducer: {
        datacraftReducer,
        appReducer
    }
})

export type RootState = ReturnType<typeof store.getState>
export type AppDispatch = typeof store.dispatch