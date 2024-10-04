const URL = 'http://localhost:8080';
let departments = [];

const findAllDepartments = async () => {
    await fetch(`${URL}/api/department`, {
        method: 'GET',
        headers: {
            "Content-Type": "application/json",
            "Accept": "application/json"
        }
    }).then(response => response.json()).then(response => {
        console.log(response);
    }).catch(console.log);
};

findAllDepartments();