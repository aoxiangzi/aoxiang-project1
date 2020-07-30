//验证身份证号
function isIdCard(card){
//身份证号码18位，前17位为数字，最后一位是校验位，可能为数字或字符X
    var reg = /^\d{17}[\d|Xx]$/;
    return reg.test(card);
}
//验证手机号码
function isPhoneNumber(number){
    var reg = /^[1]\d{10}$/;
    return reg.test(number);
}