
function say() {
  console.log('hello world')
}
let b = String.__proto__ == String.prototype
console.log(b)
String.__proto__.a = say
//console.log(String.prototype)
String.a()
