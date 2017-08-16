// VEM_showHideDiv.js - version 0.1 
//
//version   Author          Description
//0.1       Paul Seifert    Intitial js for showHidDiv functionality
//

function showHideDiv(id){ 
    var obj = document.getElementById(id); 

    if (obj.style.display=="none"){ 
        obj.style.display='block';
    }
        else if(obj.style.display=="block"){ 
            obj.style.display='none';
    }
}


