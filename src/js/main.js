const URL = 'http://localhost:8080';
let departments = [];
let employees = [];
let employee = {};
let bill = false;

const findAllDepartments = async () => {
    await fetch(`${URL}/api/department`, {
        method: 'GET',
        headers: {
            "Content-Type": "application/json",
            "Accept": "application/json"
        }
    }).then(response => response.json()).then(response => {
        console.log(response);
        departments = response.data;
    }).catch(console.log);
};

const loadData = async flag => {
    await findAllDepartments(); // Armando la lista de departmanentos
    let select = document.getElementById(flag ? 'departments' : 'u_departments'); // Recuperando el select
    let content = ''; // Variable de concatenación

    // Concatenando opciones como contenido
    departments.forEach(item => {
        content += `<option value="${item.id}">${item.name}</option>`;
    });
    // Añadir el contenido al select
    select.innerHTML = content;
}

const findAllEmployees = async () => {
    await fetch(`${URL}/api/employee`, {
        method: 'GET',
        headers: {
            "Content-Type": "application/json",
            "Accept": "application/json"
        }
    }).then(response => response.json()).then(response => {
        console.log(response);
        employees = response.data;
    }).catch(console.log);
}

const findBillByEmployee = async id => {
    await fetch(`${URL}/api/bill/employee/${id}`, {
        method: 'GET',
        headers: {
            "Content-Type": "application/json",
            "Accept": "application/json"
        }
    }).then(response => response.json()).then(response => {
        console.log(response);
        bill = response.data !== null;
    }).catch(console.log);
}

const loadTable = async () => {
    await findAllEmployees();
    console.log(employees);
    
    let tbody = document.getElementById('tbody');
    let content = ''; 

    employees.forEach((item, index) => {
        content += `<tr>
                        <th scope="row">${index + 1}</th>
                        <td>${item.fullName}</td>
                        <td>${item.eMail}</td>
                        <td>${item.department.name}</td>
                        <td class="text-center">
                            <button class="btn btn-outline-danger" data-bs-target="#deleteModal" data-bs-toggle="modal" onclick="findById(${item.id})">Eliminar</button>
                            <button class="btn btn-outline-primary" data-bs-target="#updateModal" data-bs-toggle="modal" onclick="setDataOnForm(${item.id})">Editar</button>
                        </td>
                    </tr>`;
    });
    tbody.innerHTML = content;
}

// Esta función se ejecuta una sola vez al cargar el JS
(async () => {
    await loadTable();
})();

const save = async () => {
    let form = document.getElementById('saveForm');
    employee = {
        fullName: document.getElementById('fullName').value,
        eMail: document.getElementById('eMail').value,
        department: {
            id: document.getElementById('departments').value
        }
    };

    await fetch(`${URL}/api/employee`, {
        method: 'POST',
        headers: {
            "Content-Type": "application/json",
            "Accept": "application/json"
        },
        body: JSON.stringify(employee)
    }).then(response => response.json()).then(async response => {
        console.log(response);
        employee = {};
        form.reset();
        await loadTable();
    }).catch(console.log);
}

const findById = async id => {
    await fetch(`${URL}/api/employee/${id}`, {
        method: 'GET',
        headers: {
            "Content-Type": "application/json",
            "Accept": "application/json"
        }
    }).then(response => response.json()).then(response => {
        console.log(response);
        employee = response.data;
    }).catch(console.log);
}

const setDataOnForm = async id => {
    await findById(id);
    await loadData(false);

    document.getElementById('u_fullName').value = employee.fullName;
    document.getElementById('u_eMail').value = employee.eMail;
    document.getElementById('u_departments').value = employee.department.id;
}

const update = async () => {
    let form = document.getElementById('updateForm');
    let updated = {
        fullName: document.getElementById('u_fullName').value,
        eMail: document.getElementById('u_eMail').value,
        department: {
            id: document.getElementById('u_departments').value
        }
    };

    await fetch(`${URL}/api/employee/${employee.id}`, {
        method: 'PUT',
        headers: {
            "Content-Type": "application/json",
            "Accept": "application/json"
        },
        body: JSON.stringify(updated)
    }).then(response => response.json()).then(async response => {
        console.log(response);
        employee = {};
        form.reset();
        await loadTable();
    }).catch(console.log);
}

const remove = async () => {
    await fetch(`${URL}/api/employee/${employee.id}`, {
        method: 'DELETE',
        headers: {
            "Content-Type": "application/json",
            "Accept": "application/json"
        }
    }).then(response => response.json()).then(async response => {
        console.log(response);
        employee = {};
        await loadTable();
    }).catch(console.log);
}