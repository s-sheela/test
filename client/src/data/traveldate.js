
const date = new Date();
function addDays(date, days) {
    const copy = new Date(Number(date))
    copy.setDate(date.getDate() + days)
    return copy.toDateString();
}

export const date1 = addDays(date,1);
export const date2 = addDays(date,3);
export const date3 = addDays(date,4);