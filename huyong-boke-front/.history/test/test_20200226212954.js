let regex = /xyz/is
console.log(regex.source)
console.log(typeof regex)
let res = regex.test('xyz')
console.log(res)
console.log(regex.source)
