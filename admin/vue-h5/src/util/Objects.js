Object.isNull = function(val) {
  if(val == undefined || val == null || val.trim() == '' || val.length == 0){
    return true;
  }
  return false;
};

Object.isNotNull = function(val) {
  return !Object.isNull(val);
};
exports.default = {
  name:"Object"
};
