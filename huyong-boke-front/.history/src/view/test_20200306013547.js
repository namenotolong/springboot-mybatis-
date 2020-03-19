let a = {}
function say() {
  console.log('hello world')
}
a.prototype.b = say()
a.b
