
const URL = "http://localhost:8080"


document.addEventListener("DOMContentLoaded", function () {

  //call these functions on page load
  loadWarehouses();
  loadProducts();
  loadInventory();
  addDropdown()
 
  //add these event lisiteners on page load
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

////////////////////////////////Warehouse////////////////////////////////////
async function loadWarehouses() {
  await fetch(URL + '/warehouses')
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
        updateButton.warehouse = warehouse;
        
        function populateUpdateForm(warehouse) {
          // Populate the form with warehouse name and location
          document.getElementById("updateWarehouseName").value = warehouse.name;
          document.getElementById("updateWarehouseLocation").value = warehouse.location;
          document.getElementById("updateWarehouseId").value = warehouse.id; // Assign the warehouse ID to a hidden input field
          document.getElementById("updateFormContainer").style.display = "";
          document.getElementById("warehouseFormContainer").style.display = "none";
        }
        
        updateButton.addEventListener("click", () => {
          // Perform update logic for the warehouse
          console.log("Update button clicked for warehouse:", warehouse);
          populateUpdateForm(warehouse);
        });

        let deleteButton = document.createElement("button");
        deleteButton.innerText = "Delete";
        deleteButton.classList.add("btn", "btn-primary", "delete-button");
        deleteButton.addEventListener("click", () => {
          // delete logic for the warehouse
          console.log("Delete button clicked for warehouse:", warehouse);
          // delete logic for the warehouse
            // check to see if warehouse has inventory
          if (warehouse.inventories.length > 0){
            alert("Cannot Delete, " + warehouse.name +  " has inventory")
          }
          else {
            // Prompt for confirmation before deleting
            if (confirm("Are you sure you want to delete this warehouse?")) {
              // Delete the warehouse
              fetch(URL + `/warehouses/${warehouse.id}`, {
                method: "DELETE",
                headers: {
                  "Content-Type": "application/json"
                }
              })
                .then(() => {
                  // Reload the warehouses after delete
                  loadWarehouses();
                  addDropdown()
                  //loadInventory()
                })
                .catch(error => {
                  console.error("Error deleting warehouse:", error);
                });
              }
            }
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

  // Add event listener to the update delete button
  let updateDeleteButton = document.getElementById("updateDeleteButton");
  updateDeleteButton.addEventListener("click", () => {
    document.getElementById("updateFormContainer").style.display = "none";
    document.getElementById("warehouseFormContainer").style.display = "";
  });
  
  // Add event listener to the update form submit button
  document.getElementById("updateForm").addEventListener("submit", handleUpdateFormSubmit);
}

function handleUpdateFormSubmit(event) {
  event.preventDefault();
  
  // Retrieve the updated name, location, and ID values
  let warehouseId = document.getElementById("updateWarehouseId").value;
  let warehouseName = document.getElementById("updateWarehouseName").value;
  let warehouseLocation = document.getElementById("updateWarehouseLocation").value;

  // Send a PUT request to update the warehouse
  fetch(URL +`/warehouses/${warehouseId}`, {
    method: "PUT",
    headers: {
      "Content-Type": "application/json"
    },
    body: JSON.stringify({ name: warehouseName, location: warehouseLocation })
  })
    .then(() => {
      // Hide the update form and display the warehouse list
      document.getElementById("updateFormContainer").style.display = "none";
      document.getElementById("warehouseFormContainer").style.display = "";

      // Reload the warehouses after update
      loadWarehouses();
      loadInventory()
      addDropdown();
    })
    .catch(error => {
      console.error("Error updating warehouse:", error);
    });
}
/////////////////////////////////////////////////////////////////////////////


/////////////////////////////Add Warehouse///////////////////////////////////
function addWarehouse() {
  let warehouseName = document.getElementById("warehouseName").value;
  let warehouseLocation = document.getElementById("warehouseLocation").value;

  fetch(URL +"/warehouses", {
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
          addDropdown();
      });
}
////////////////////////////////////////////////////////////////////////////////




/////////////////////////////LoadProduct//////////////////////////////////////////
async function loadProducts() {
  await fetch(URL+"/products")
    .then(response => response.json())
    .then(data => {
      console.log(data)
      let productTableBody = document.getElementById("productTableBody");
      productTableBody.innerHTML = "";
      data.forEach(product => {
        let row = document.createElement("tr");
        let productCell = document.createElement("td");
        let priceCell = document.createElement("td");
        productCell.innerText = product.name;
        priceCell.innerText = product.price;
        let actionsCell = document.createElement("td");
        

        function populateUpdateProductForm(product) {
          
          // Populate the form with product name and price
          document.getElementById("updateProductName").value = product.name;
          document.getElementById("updatePrice").value = product.price;
          document.getElementById("updateProductId").value = product.id;
          document.getElementById("updateProductContainer").style.display = "";
          document.getElementById("productFormContainer").style.display ="none";
        }


        let updateButton = document.createElement("button");
        updateButton.innerText = "Update";
        updateButton.classList.add("btn", "btn-primary", "space");
        updateButton.addEventListener("click", () => {
          // Perform update logic for the product
          console.log("Update button clicked for product:", product);
          populateUpdateProductForm(product);
        });
        
        let deleteButton = document.createElement("button");
        deleteButton.innerText = "Delete";
        deleteButton.classList.add("btn", "btn-primary", "delete-button");
        deleteButton.addEventListener("click", () => {
          // delete logic for the warehouse
          console.log("Delete button clicked for product:", product);
          // delete logic for the warehouse
            // check to see if warehouse has inventory
            
            console.log(product)
            console.log(product.inventories.length)
          if (product.inventories.length > 0){
            alert("Cannot Delete, " + product.name +  " is in inventory")
          }
          else {
            // Prompt for confirmation before deleting
            if (confirm("Are you sure you want to delete this product?")) {
              // Delete the warehouse
              fetch(URL +`/products/${product.id}`, {
                method: "DELETE",
                headers: {
                  "Content-Type": "application/json"
                }
              })
                .then(() => {
                  // Reload the warehouses and the dropdown function to remove the product from the dropdown
                  loadProducts();
                  //loadWarehouses();
                  addDropdown()
                  //loadInventory();
                })
                .catch(error => {
                  console.error("Error deleting product:", error);
                });
              }
            }
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

    let updateDeleteButton = document.getElementById("cancelProductUpdateButton");
    updateDeleteButton.addEventListener("click", () => {
      document.getElementById("updateProductContainer").style.display = "none";
      document.getElementById("productFormContainer").style.display ="";
    });

    document.getElementById("updateProduct").addEventListener("submit", handleUpdateProductFormSubmit);
}
//////////////////////////////////////////////////////////////////////////////////////////


////////////////////Logic to handle the updating form being submitted/////////////////////
function handleUpdateProductFormSubmit(event) {
event.preventDefault();
let productId = document.getElementById("updateProductId").value;
console.log(productId)
let updateProduct = document.getElementById("updateProductName").value;
let updatePrice = document.getElementById("updatePrice").value;
console.log(updatePrice)
  fetch(URL + `/products/${productId}`, {
    method: "PUT",
    headers: {
      "Content-Type": "application/json"
    },
    body: JSON.stringify({name: updateProduct, price: updatePrice})
  })
    .then(() => {
      // Hide the update form and display the product list
      document.getElementById("updateProductContainer").style.display = "none";
      document.getElementById("productFormContainer").style.display ="";

      loadProducts();
      //loadWarehouses();
      loadInventory();
      addDropdown();
    })
    .catch(error => {
      console.error("Error updating product:", error);
    });
}
/////////////////////////////////////////////////////////////////////////////////////////////


//////////////////////Delete a Product///////////////////////////////////////////////////
function deleteProduct(productId) {
  if (confirm("Are you sure you want to delete this product?")) {
    fetch(URL +`/products/${productId}`, {
      method: "DELETE",
      headers: {
        "Content-Type": "application/json"
      }
    })
      .then(() => {
        loadProducts();
        //loadWarehouses();
        addDropdown();
      })
      .catch(error => {
        console.error("Error deleting product:", error);
      });
  }
}
///////////////////////////////////////////////////////////////////////////////////////////////



/////////////////// Add a new product//////////////////////////////////////////////////////////
function addInventory() {
  let warehouseId = document.getElementById("warehouseSelect").value;
  let productId = document.getElementById("productSelect").value;
  let quantity = document.getElementById("inventoryQuantity").value;

  // Perform a check if the inventory item already exists
  fetch(URL +`/inventory`)
    .then(response => response.json())
    .then(data => {
      let addProduct = "yes";
      for(i=0; i < data.length; i++){
        console.log(data)
        console.log(data[i][1] + " " +productId + data[i][3] + " "  + warehouseId)
        if(data[i][1] ==  warehouseId && data[i][3] == productId){
          alert("Product is already in Inventory, please use update")
          addProduct = "no"
          //addDropdown();
          document.getElementById("inventoryQuantity").value = "";
          break;

        }
      }  
        if(addProduct == "yes"){
            // Inventory item does not exist, proceed with adding it
            fetch(URL + `/inventory/add?warehouseId=${warehouseId}&productId=${productId}&quantity=${quantity}`, {
              method: "POST",
              headers: {
                "Content-Type": "application/json"
              }
            })
              .then(() => {
                document.getElementById("inventoryQuantity").value = "";
                // loadWarehouses();
             
                setTimeout(loadProducts, 3000); // Delay loadProducts() by 1 second
                setTimeout(loadWarehouses, 3000); // Delay loadWarehouses() by 1 second
                addDropdown();
                setTimeout(loadInventory,1000);
            
                //alert("Inventory has been added")
              });
            } 
        
      });
}






/////////////////////////////////Inventory///////////////////////////////////
async function loadInventory() {
  await fetch(URL + "/inventory")
    .then(response => response.json())
    .then(data => {
      let inventoryTableBody = document.getElementById("inventoryTableBody");
      inventoryTableBody.innerHTML = "";
      data.forEach(inventory => {
        let row = document.createElement("tr");
        let inventoryCell = document.createElement("td");
        let productCell = document.createElement("td");
        let quantityCell = document.createElement("td");
        let actionsCell = document.createElement("td");
        inventoryCell.innerText = inventory[2];
        productCell.innerText = inventory[4];
        quantityCell.innerText = inventory[5];
        
        let updateButton = document.createElement("button");
        updateButton.innerText = "Update";
        updateButton.classList.add("btn", "btn-primary", "space");
        updateButton.addEventListener("click", () => {
          // Perform update logic for the warehouse
          console.log("Update button clicked for inventory:", inventory);
          populateUpdateInventoryForm(inventory);
        });
        
    
        let deleteButton = document.createElement("button");
        deleteButton.innerText = "Delete";
        deleteButton.classList.add("btn", "btn-primary", "delete-button");
        deleteButton.addEventListener("click", () => {
          // Perform delete logic for the warehouse
          console.log("Delete button clicked for inventory:", inventory);
          console.log(inventory[0]);
          deleteInventory(inventory);
          //loadProducts()
          //loadWarehouses();
          //loadInventory();
        });
        
        actionsCell.appendChild(updateButton);
        actionsCell.appendChild(deleteButton);
        
        row.appendChild(inventoryCell);
        row.appendChild(productCell);
        row.appendChild(quantityCell);
        row.appendChild(actionsCell);
        
        inventoryTableBody.appendChild(row);
      });
    })
    .catch(error => {
      console.error("Error loading inventory:", error);
    });
}

function populateUpdateInventoryForm(inventory) {
  // Populate the form with inventory data
  console.log(inventory)
  document.getElementById("updateId").value =inventory[0];
  document.getElementById("updateWarehouseSelect").value = inventory[2];
  document.getElementById("updateProductSelect").value = inventory[4];
  document.getElementById("UpdateInventoryQuantity").value = inventory[5];
  document.getElementById("updateInventoryForms").style.display = "";
  document.getElementById("addInventoryForm").style.display = "none";
}


let updateDeleteButton = document.getElementById("cancelInventoryUpdateButton");
updateDeleteButton.addEventListener("click", () => {
  document.getElementById("updateInventoryForms").style.display = "none";
  document.getElementById("addInventoryForm").style.display ="";
});

// Add event listener to the inventory update form submit button
document.getElementById("updateInventoryForm").addEventListener("submit", handleUpdateInventoryFormSubmit);
function handleUpdateInventoryFormSubmit(event) {
  event.preventDefault();
  
  // Retrieve the updated values from the form
  let updateId =  document.getElementById("updateId").value
  let warehouseId = document.getElementById("updateWarehouseSelect").value;
  let productId = document.getElementById("updateProductSelect").value;
  let quantity = document.getElementById("UpdateInventoryQuantity").value;

  // Send a PUT request to update the inventory
  if(quantity > 0 && quantity < 101){
  fetch(URL +`/inventory/${updateId}`, {
    method: "PUT",
    headers: {
      "Content-Type": "application/json"
    },
    body: JSON.stringify({quantity})
  })
    .then(() => {
      // Hide the update form and display the inventory table
      document.getElementById("updateInventoryForms").style.display = "none";
      document.getElementById("addInventoryForm").style.display = "";
      
      // Reload the inventory table after update
      loadProducts()
      loadWarehouses();
      loadInventory();
    })
    .catch(error => {
      console.error("Error updating inventory:", error);
    });
  }else {
    alert("quantity can't be 0, Use delete button")
  }
  }


function deleteInventory(inventoryId) {
  console.log(inventoryId[0])
  if (confirm("Are you sure you want to delete this inventory item?")) {
    console.log(inventoryId[0])
    fetch(URL +`/inventory/${inventoryId[0]}`, {
      method: "DELETE",
      headers: {
        "Content-Type": "application/json"
      }
    })
      .then(() => {
        // Reload the inventory table after deleting
        loadInventory();
      })
      .catch(error => {
        console.error("Error deleting inventory item:", error);
      });
  }
}



///////////////////////////////Used to proide the dropdown options to the add inventory form////
 function addDropdown() {
  let warehouseSelect = document.getElementById("warehouseSelect");
  let productSelect = document.getElementById("productSelect");
  // Fetch warehouse options
   fetch(URL + '/warehouses')
    .then(response => response.json())
    .then(data => {
      warehouseSelect.innerHTML = "";
      let warehouseDefaultOption = document.createElement("option");
      warehouseDefaultOption.value = "";
      warehouseDefaultOption.innerText = "Select Warehouse";
      warehouseDefaultOption.disabled = true; // Add the disabled attribute
      warehouseDefaultOption.selected = true; // Optionally, select the default option
      warehouseSelect.appendChild(warehouseDefaultOption);

      data.forEach(warehouse => {
        let warehouseOption = document.createElement("option");
        warehouseOption.value = warehouse.id;
        warehouseOption.innerText = `${warehouse.name} - ${warehouse.location}`;
        warehouseSelect.appendChild(warehouseOption);
      });
    })
    .catch(error => {
      console.error("Error loading warehouses:", error);
    });
    
  //Get and assign product options
  fetch(URL+"/products")
    .then(response => response.json())
    .then(data => {
      productSelect.innerHTML = "";
      let productDefaultOption = document.createElement("option");
      productDefaultOption.value = "";
      productDefaultOption.innerText = "Select Product";
      productDefaultOption.disabled = true; 
      productDefaultOption.selected = true;
      productSelect.appendChild(productDefaultOption)
      data.forEach(product => {
        let productOption = document.createElement("option");
        productOption.value = product.id;
        productOption.innerText = `${product.name} - $${product.price}`;
        productSelect.appendChild(productOption);
        // loadInventory();
        // loadProducts();
    
      });
    })
    .catch(error => {
      console.error("Error loading products:", error);
    });

    
  }

  /////////////////////////////////////////////////////////////////////////////////////////////
 
  



  // Add a new product using the API
function addProduct() {
  let productName = document.getElementById("productName").value;
  let productPrice = document.getElementById("productPrice").value;

  fetch(URL+"/products", {
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
      //loadInventory();
      //loadWarehouses();
      addDropdown();
  });
}
