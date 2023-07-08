document.addEventListener("DOMContentLoaded", function () {
  loadWarehouses();
  loadProducts();
  loadInventory();
  addDropdown()
 

  document.getElementById("warehouseForm").addEventListener("submit", function (event) {
      event.preventDefault();
      addWarehouse();
  });

  document.getElementById("productForm").addEventListener("submit", function (event) {
      event.preventDefault();
      addProduct();
  });

  document.getElementById("inventoryForm").addEventListener("submit", function (event) {
      event.preventDefault();
      addInventory();
  });
});

//Fetch all warehouses from the API
async function loadWarehouses() {
  await fetch("/warehouses")
    .then(response => response.json())
    .then(data => {
      let warehouseTableBody = document.getElementById("warehouseTableBody");
      warehouseTableBody.innerHTML = "";
      data.forEach(warehouse => {
        let row = document.createElement("tr");
        let nameCell = document.createElement("td");
        let locationCell = document.createElement("td");
        nameCell.innerText = warehouse.name;
        locationCell.innerText = warehouse.location;
        let actionsCell = document.createElement("td");
        
        let updateButton = document.createElement("button");
        updateButton.innerText = "Update";
        updateButton.classList.add("btn", "btn-primary", "space");
        updateButton.addEventListener("click", () => {
          // Perform update logic for the warehouse
          console.log("Update button clicked for warehouse:", warehouse);
        });
        
      


        let deleteButton = document.createElement("button");
        deleteButton.innerText = "Delete";
        deleteButton.classList.add("btn", "btn-primary", "delete-button");
        deleteButton.addEventListener("click", () => {
          // Perform delete logic for the warehouse
          console.log("Delete button clicked for warehouse:", warehouse);
        });
        
        actionsCell.appendChild(updateButton);
        actionsCell.appendChild(deleteButton);
        
        row.appendChild(nameCell);
        row.appendChild(locationCell);
        row.appendChild(actionsCell);
        
        warehouseTableBody.appendChild(row);
      });
    })
    .catch(error => {
      console.error("Error loading warehouses:", error);
    });
}

async function loadProducts() {
  await fetch("/products")
    .then(response => response.json())
    .then(data => {
      let productTableBody = document.getElementById("productTableBody");
      productTableBody.innerHTML = "";
      data.forEach(product => {
        let row = document.createElement("tr");
        let productCell = document.createElement("td");
        let priceCell = document.createElement("td");
        productCell.innerText = product.name;
        priceCell.innerText = product.price;
        let actionsCell = document.createElement("td");
        
        let updateButton = document.createElement("button");
        updateButton.innerText = "Update";
        updateButton.classList.add("btn", "btn-primary", "space");
        updateButton.addEventListener("click", () => {
          // Perform update logic for the warehouse
          console.log("Update button clicked for product:", product);
        });
        
    
        let deleteButton = document.createElement("button");
        deleteButton.innerText = "Delete";
        deleteButton.classList.add("btn", "btn-primary", "delete-button");
        deleteButton.addEventListener("click", () => {
          // Perform delete logic for the warehouse
          console.log("Delete button clicked for product:", product);
        });
        
        actionsCell.appendChild(updateButton);
        actionsCell.appendChild(deleteButton);
        
        row.appendChild(productCell);
        row.appendChild(priceCell);
        row.appendChild(actionsCell);
        
        productTableBody.appendChild(row);
      });
    })
    .catch(error => {
      console.error("Error loading products:", error);
    });
}

// // Fetch all products from the API
// async function loadProducts() {
//   await fetch("/products")
//       .then(response => response.json())
//       .then(data => {
//           let productList = document.getElementById("productList");
//           productList.innerHTML = "";
//           data.forEach(product => {
//               let productItem = document.createElement("div");
//               productItem.innerText = `${product.name} - $${product.price}`;
//               productList.appendChild(productItem);
//           });
//       });
// }


async function loadInventory() {
  let inventoryList = document.getElementById("inventoryList");
  inventoryList.innerHTML = "";
  await fetch("/inventory")
    .then(response => response.json())
    .then(data => {
      data.forEach(item => {
        let inventoryItem = document.createElement("div");
        inventoryItem.innerText = `${item[1]} - ${item[2]} - ${item[3]}`;
        inventoryList.appendChild(inventoryItem);
      });
    })
    .catch(error => {
      console.error("Error loading inventory:", error);
    });
}

function addWarehouse() {
  console.log("Hello")
  let warehouseName = document.getElementById("warehouseName").value;
  let warehouseLocation = document.getElementById("warehouseLocation").value;

  fetch("/warehouses", {
      method: "POST",
      headers: {
          "Content-Type": "application/json"
      },
      body: JSON.stringify({ name: warehouseName, location: warehouseLocation })
  })
      .then(() => {
          document.getElementById("warehouseName").value = "";
          document.getElementById("warehouseLocation").value = "";
          loadWarehouses();
      });
}

// Add a new product using the API
function addProduct() {
  let productName = document.getElementById("productName").value;
  let productPrice = document.getElementById("productPrice").value;

  fetch("/products", {
      method: "POST",
      headers: {
          "Content-Type": "application/json"
      },
      body: JSON.stringify({ name: productName, price: parseFloat(productPrice) })
  })
      .then(() => {
          document.getElementById("productName").value = "";
          document.getElementById("productPrice").value = "";
          loadProducts();
      });
}



 function addDropdown() {
  console.log("called")
  let warehouseSelect = document.getElementById("warehouseSelect");
  let productSelect = document.getElementById("productSelect");
  let quantity = document.getElementById("inventoryQuantity").value;

  // Fetch warehouse options
   fetch("/warehouses")
    .then(response => response.json())
    .then(data => {
      warehouseSelect.innerHTML = "";
      data.forEach(warehouse => {
        // console.log(warehouse)
        let warehouseOption = document.createElement("option");
        warehouseOption.value = warehouse.id;
        warehouseOption.innerText = `${warehouse.name} - ${warehouse.location}`;
        warehouseSelect.appendChild(warehouseOption);
      });
    })
    .catch(error => {
      console.error("Error loading warehouses:", error);
    });

  // Fetch product options
  fetch("/products")
    .then(response => response.json())
    .then(data => {
      productSelect.innerHTML = "";
      data.forEach(product => {
        let productOption = document.createElement("option");
        productOption.value = product.id;
        productOption.innerText = `${product.name} - $${product.price}`;
        productSelect.appendChild(productOption);
      });
    })
    .catch(error => {
      console.error("Error loading products:", error);
    });
  }
 
  
  function addInventory() {
    let warehouseId = document.getElementById("warehouseSelect").value;
    let productId = document.getElementById("productSelect").value;
    let quantity = document.getElementById("inventoryQuantity").value;
  
    fetch(`/inventory/add?warehouseId=${warehouseId}&productId=${productId}&quantity=${quantity}`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json"
      }
    })
      .then(() => {
        document.getElementById("inventoryQuantity").value = "";
        loadInventory();
      });
  }
