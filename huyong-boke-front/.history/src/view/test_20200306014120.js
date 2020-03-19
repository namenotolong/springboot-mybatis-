
function say() {
  console.log('hello world')
}
String.__proto__.a = say
//console.log(String.prototype)
String.a
