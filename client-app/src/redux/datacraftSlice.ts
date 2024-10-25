import { createSlice, PayloadAction } from "@reduxjs/toolkit"
import { RootState } from "./store"

export type DatacraftState = {
    id: number
}

const initialState = (): DatacraftState => {
    let dataCraft = {
        id: 1
    }

    return dataCraft
}

export const datacraftSlice = createSlice({
    name: 'datacraftState',
    initialState: initialState(),
    reducers: {
        setDatacraftId(state: DatacraftState, action: PayloadAction<number>) {
            state.id = action.payload
        }
    }
});

export const { setDatacraftId } = datacraftSlice.actions
export default datacraftSlice.reducer

