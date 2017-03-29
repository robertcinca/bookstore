//Viewcart.html specific functions

//Function that extracts cart data from JSON format and converts it into a table display
function getShoppingCart() {
    var x = getParameterByName('username'); //get username
    var cartInfo = getAccounts(x); //get JSON from function
    var totalAmount = 0; //Calculate total price of all purchased books
    //Extracting Table headings
    var col = [];
    for (var i = 0; i <= cartInfo.length; i++) {
        if (i == cartInfo.length) {
             col.push(key);
             break;
        }

        for (var key in cartInfo[i]) {
            if (col.indexOf(key) === -1) {
                col.push(key);
            }
        }
    }
    
    // Create table
    var table = document.createElement("table");
    
    // Create table header row with extracted headings
    var tr = table.insertRow(-1);                   
    
    for (var i = 0; i < col.length; i++) {
        var th = document.createElement("th");     
        if (i == col.length - 1) {
            th.innerHTML = "Total Price"; //create extra column
        }
        else {
            th.innerHTML = col[i];
        }
        tr.appendChild(th);
    }
    
    // Add JSON to table
    for (var i = 0; i < cartInfo.length; i++) {
        tr = table.insertRow(-1);
        
    //tabCell.innerHTML = (cartInfo[i][col[j]]).link("accountdetail.html" + "?accNo=" + (cartInfo[i][col[j]]));

        for (var j = 0; j < 2; j++) {
            var tabCell = tr.insertCell(-1);
                tabCell.innerHTML = cartInfo[i][col[j]];
        }

        for (var j = 2; j < 3; j++) {
            var x = cartInfo[i][col[j]];
            tr.insertCell(-1).innerHTML = '<select name="quantity" onchange="javascript:quantityChange()"><option value=' + eval('x') + '>' + eval('x') + '</option><option value="0">0</option><option value="1">1</option><option value="2">2</option><option value="3">3</option><option value="4">4</option><<option value="5">5</option>/select>';
        }

        for (var j = 3; j < col.length - 1; j++) {
            var tabCell = tr.insertCell(-1);
            tabCell.innerHTML = cartInfo[i][col[j]];
        }

        //Multiply Quantity with Price to get Total Price per row
        //Add to Total Amount to get Total Overall Price
        for (var j = col.length - 1; j < col.length; j++) {
            var tabCell = tr.insertCell(-1);
                tabCell.innerHTML = parseInt(cartInfo[i][col[col.length -2]]) * parseInt(cartInfo[i][col[col.length -4]]);
                totalAmount += parseInt(cartInfo[i][col[col.length -2]]) * parseInt(cartInfo[i][col[col.length -4]]);
        }
    }

    //Create last row
    var footer = table.createTFoot();
    tr = footer.insertRow(-1);
    for (var j = 0; j < col.length; j++) {
        var tabCell = tr.insertCell(-1);
        if (j == col.length - 2)
            tabCell.innerHTML = "Total Price (HKD):";
        if (j == col.length - 1)
            tabCell.innerHTML = totalAmount + ".00";
    }
    
    // Add table with JSON data to a container
    var divContainer = document.getElementById("showData");
    divContainer.innerHTML = "";
    divContainer.appendChild(table);
}

//TODO: handle quantity change
function quantityChange() {
    alert("quantity has changed!");
}

//Fake JSON data, need to link to a DB with real data
function getAccounts(user) {
    var from, to, selected=new Array();
    var accDetail=
        [
         {
            "Book Name":"Harry Potter and the Philosopher's Stone",
            "Author":"J. K. Rowling",
            "Quantity": 3,
            "Unit Points": 20,
            "Unit Price": 130,
         },
         {
            "Book Name":"Pride and Prejudice",
            "Author":"Jane Austen",
            "Quantity": 4,
            "Unit Points": 10,
            "Unit Price": 140,
         },
         {
            "Book Name":"Enchantment",
            "Author":"Guy Kawasaki",
            "Quantity": 2,
            "Unit Points": 50,
            "Unit Price": 100,
         },
         {
            "Book Name":"An Introduction to Social Psychology",
            "Author":"Miles Hewstone",
            "Quantity": 1,
            "Unit Points": 100,
            "Unit Price": 860,
         },
         {
            "Book Name":"The Lord of the Rings",
            "Author":"J.R.R. Tolkien",
            "Quantity": 1,
            "Unit Points": 15,
            "Unit Price": 250,
         },
         {
            "Book Name":"Never Let Me Go",
            "Author":"Kazuo Ishiguro",
            "Quantity": 2,
            "Unit Points": 10,
            "Unit Price": 70,
         }      
        ];
        from=Math.floor(Math.random()*6);
        to=Math.floor(Math.random()*6+1);
        for (i=0; i<to; i++) {
            selected[i]=accDetail[from];
            from=(from+1)%6;
        }
        return selected;
}