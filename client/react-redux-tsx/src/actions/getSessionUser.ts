
export const getUser =() =>{
    const userData: string | null = sessionStorage.getItem('myData');
    if(userData!=null) {
        return JSON.parse(userData);
    }
    else{
        return null;
    }
};
