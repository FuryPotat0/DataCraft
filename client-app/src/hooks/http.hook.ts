import { useCallback } from "react"
import { toast } from "react-toastify"

export const useHttp = () => {

    const request = useCallback(async<T>(address: string,
        method: "GET" | "POST" | "PUT" | "PATCH" | "DELETE" = "GET",
        body: any = null,
        headers: { name: string, value: string }[] = []) => {
        const preparedHeaders = new Headers()
        headers?.forEach(header => {
            preparedHeaders.append(header.name, header.value)
        })

        let response: any
        try {
            const preparedBody = body instanceof FormData
                ? body
                : JSON.stringify(body)

            response = method === "GET"
                ? await fetch(address, { method: method, headers: preparedHeaders })
                : await fetch(address, { method: method, headers: preparedHeaders, body: preparedBody })
        } catch (ex) {
            const err = ex as Error
            toast.error(err.message)
            throw (ex)
        } finally {

        }

        const json = await response.json()
        if (response.ok) {
            return json as T
        }

        let errorText = ""
        if (json.hasOwnProperty("Error")) {
            errorText = json.Error
        }

        toast.error(errorText)
        throw new Error(errorText)
    }, [])

    return { request }
}