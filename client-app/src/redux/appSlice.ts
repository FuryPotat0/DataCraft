import { createSlice, PayloadAction } from "@reduxjs/toolkit"

export type AppState = {
    requestInProgress: number
}

const initialState = (): AppState => {
    let app = {
        requestInProgress: 0
    }

    return app
}

export const appSlice = createSlice({
    name: 'app',
    initialState: initialState(),
    reducers: {
        onRequestStarted(state: AppState) {
            state.requestInProgress += 1
        },

        onRequestCompleted(state: AppState) {
            state.requestInProgress -= 1
        }
    }
})

export const { onRequestStarted, onRequestCompleted } = appSlice.actions
export default appSlice.reducer