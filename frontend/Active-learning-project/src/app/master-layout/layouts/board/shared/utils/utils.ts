const getTimeFormattedString = (hours: number, minutes: number, seconds: number): string => {
    return `${hours < 10 ? `0${hours}` : hours}:${minutes < 10 ? `0${minutes}` : minutes}:${seconds < 10 ? `0${seconds}` : seconds}`
}

const getTimeFormated = (dateObject: Date): string => {
    const hours = dateObject.getHours();
    const minutes = dateObject.getMinutes();
    const seconds = dateObject.getSeconds();

    return `${hours < 10 ? `0${hours}` : hours}:${minutes < 10 ? `0${minutes}` : minutes}:${seconds < 10 ? `0${seconds}` : seconds}`
}

const setSessionStoragePairs = (key: Array<string> | string, value: Array<string> | string): void => {
    if (typeof key !== typeof value) return console.log("Key-Value pairs must be of the same type");

    if (typeof key === "string" && typeof value === "string") {
        sessionStorage.setItem(key, value);
        return;
    }

    if (key.length !== value.length) return console.log("Key-value pair array must be of the same length");

    for (let i = 0; i < key.length; i++) sessionStorage.setItem(key[i], value[i]);
}

const getSessionStorageValue = (key: string): string | null => sessionStorage.getItem(key);

const removeSessionStoragePair = (key: string): void => sessionStorage.removeItem(key);

export {
    getTimeFormattedString,
    setSessionStoragePairs,
    getSessionStorageValue,
    removeSessionStoragePair,
    getTimeFormated
}