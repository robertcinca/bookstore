//Function that extracts cart data from JSON format and converts it into a table display
function getShoppingCart() {
    var x = getParameterByName('username'); //get username
    var cartInfo = getAccounts(x); //get JSON from function
    var totalAmount = 0; //Calculate total price of all purchased books
    
    //Extracting Table headings
    var col = [];
    for (var i = 0; i < cartInfo.length; i++) {
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
        th.innerHTML = col[i];
        tr.appendChild(th);
    }
    
    // Add JSON to table
    for (var i = 0; i < cartInfo.length; i++) {
        
        tr = table.insertRow(-1);
        
        // for (var j = 0; j < 1; j++) {
        //     var tabCell = tr.insertCell(-1);
        //     tabCell.innerHTML = (cartInfo[i][col[j]]).link("accountdetail.html" + "?accNo=" + (cartInfo[i][col[j]]));
        // }
        for (var j = 0; j < col.length; j++) {
            var tabCell = tr.insertCell(-1);
            tabCell.innerHTML = cartInfo[i][col[j]];
            if (cartInfo[i][col[col.length - 1]]) {
                totalAmount += parseInt(cartInfo[i][col[col.length -1]]);
                alert(parseInt(cartInfo[i][col[col.length -1]]));
            }
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
            tabCell.innerHTML = totalAmount;
    }

    
    // Add table with JSON data to a container
    var divContainer = document.getElementById("showData");
    divContainer.innerHTML = "";
    divContainer.appendChild(table);
}


//Fake JSON data, need to link to a DB with real data
function getAccounts(user) {
    var from, to, selected=new Array();
    var accDetail=
        [
         {
            "Book Name":"Harry Potter and the Philosopher's Stone",
            "Author":"J. K. Rowling",
            "Quantity": 1,
            "Unit Point": 20,
            "Unit Price": 130,
            "Total Price": 20
         },
         {
            "Book Name":"Pride and Prejudice",
            "Author":"Jane Austen",
            "Quantity": 4,
            "Unit Point": 10,
            "Unit Price": 140,
            "Total Price": 560
         },
         {
            "Book Name":"Harry Potter and the Philosopher's Stone",
            "Author":"J. K. Rowling",
            "Quantity": 1,
            "Unit Point": 20,
            "Unit Price": 20,
            "Total Price": 20
         },
         {
            "Book Name":"Pride and Prejudice",
            "Author":"Jane Austen",
            "Quantity": 4,
            "Unit Point": 10,
            "Unit Price": 140,
            "Total Price": 560
         },
         {
            "Book Name":"Harry Potter and the Philosopher's Stone",
            "Author":"J. K. Rowling",
            "Quantity": 1,
            "Unit Point": 20,
            "Unit Price": 20,
            "Total Price": 20
         },
         {
            "Book Name":"Pride and Prejudice",
            "Author":"Jane Austen",
            "Quantity": 4,
            "Unit Point": 10,
            "Unit Price": 140,
            "Total Price": 560
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