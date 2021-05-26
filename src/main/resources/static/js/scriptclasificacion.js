(function() {
    
    // get references to select list and display text box
    var sel = document.getElementById('clasificacion');
    var el = document.getElementById('display');


    function getSelectedOption(sel) {
        var opt;
        for ( var i = 0, len = sel.options.length; i < len; i++ ) {
            opt = sel.options[i];
            if ( opt.selected === true ) {
                break;
            }
        }
        return opt;
    }
    
    document.getElementById('showTxt').onclick = function () {
        // access text property of selected option
        el.value = sel.options[sel.selectedIndex].text;
    }

    
}());