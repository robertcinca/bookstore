//Function that extracts cart data from JSON format and converts it into a table display
function getShoppingCart() {
    var x = getParameterByName('username'); //get username
    var accountInfo = getAccounts(x); //get JSON from function
    
    //Extracting Table headings
    var col = [];
    for (var i = 0; i < accountInfo.length; i++) {
        for (var key in accountInfo[i]) {
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
    for (var i = 0; i < accountInfo.length; i++) {
        
        tr = table.insertRow(-1);
        
        // for (var j = 0; j < 1; j++) {
        //     var tabCell = tr.insertCell(-1);
        //     tabCell.innerHTML = (accountInfo[i][col[j]]).link("accountdetail.html" + "?accNo=" + (accountInfo[i][col[j]]));
        // }
        for (var j = 0; j < col.length; j++) {
            var tabCell = tr.insertCell(-1);
            tabCell.innerHTML = accountInfo[i][col[j]];
        }
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
            "Unit Price": 130.00,
            "Total Price": 20
         },
         {
            "Book Name":"Pride and Prejudice",
            "Author":"Jane Austen",
            "Quantity": 4,
            "Unit Point": 10,
            "Unit Price": 140.00,
            "Total Price": 560.00
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
            "Unit Price": 140.00,
            "Total Price": 560.00
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
            "Unit Price": 140.00,
            "Total Price": 560.00
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