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
          let warehouseList = document.getElementById("warehouseList");
          warehouseList.innerHTML = "";
          data.forEach(warehouse => {
              let warehouseItem = document.createElement("div");
              warehouseItem.innerText = `${warehouse.name} - ${warehouse.location}`;
              warehouseList.appendChild(warehouseItem);
          });
      });
}


// Fetch all products from the API
async function loadProducts() {
  await fetch("/products")
      .then(response => response.json())
      .then(data => {
          let productList = document.getElementById("productList");
          productList.innerHTML = "";
          data.forEach(product => {
              let productItem = document.createElement("div");
              productItem.innerText = `${product.name} - $${product.price}`;
              productList.appendChild(productItem);
          });
      });
}


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
