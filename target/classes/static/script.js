const apiUrl = "http://localhost:8080/properties"; // Ajusta según tu backend

document.addEventListener("DOMContentLoaded", loadProperties);
document.getElementById("propertyForm").addEventListener("submit", addProperty);

function loadProperties() {
    fetch(apiUrl)
        .then(response => response.json())
        .then(properties => {
            const tableBody = document.getElementById("propertyList");
            tableBody.innerHTML = "";
            properties.forEach(prop => {
                tableBody.innerHTML += `
                    <tr>
                        <td>${prop.id}</td>
                        <td>${prop.address}</td>
                        <td>$${prop.price}</td>
                        <td>${prop.size} m²</td>
                        <td>${prop.description}</td>
                        <td>
                            <button onclick="editProperty(${prop.id})">Edit</button>
                            <button onclick="deleteProperty(${prop.id})">Delete</button>
                        </td>
                    </tr>`;
            });
        });
}

function addProperty(event) {
    event.preventDefault();
    const newProperty = {
        address: document.getElementById("address").value,
        price: parseFloat(document.getElementById("price").value),
        size: parseFloat(document.getElementById("size").value),
        description: document.getElementById("description").value
    };

    fetch(apiUrl, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(newProperty)
    }).then(() => {
        document.getElementById("propertyForm").reset();
        loadProperties();
    });
}

function deleteProperty(id) {
    fetch(`${apiUrl}/${id}`, { method: "DELETE" })
        .then(() => loadProperties());
}

function editProperty(id) {
    const newAddress = prompt("New Address:");
    const newPrice = prompt("New Price:");
    const newSize = prompt("New Size:");
    const newDescription = prompt("New Description:");

    if (newAddress && newPrice && newSize && newDescription) {
        const updatedProperty = {
            address: newAddress,
            price: parseFloat(newPrice),
            size: parseFloat(newSize),
            description: newDescription
        };

        fetch(`${apiUrl}/${id}`, {
            method: "PUT",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(updatedProperty)
        }).then(() => loadProperties());
    }
}
