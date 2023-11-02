function invalidMsg(textbox) {
    if (textbox.value === '') {
        textbox.setCustomValidity
            ('Por favor ingrese elemento a buscar.');
    } else if (textbox.validity.typeMismatch) {
        textbox.setCustomValidity
            ('');
    } else {
        textbox.setCustomValidity('');
    }
    return true;
} 