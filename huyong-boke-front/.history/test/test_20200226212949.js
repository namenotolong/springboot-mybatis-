let regex = /xyz/i
console.log(regex.source)
console.log(typeof regex)
let res = regex.test('xyz')
console.log(res)
console.log(regex.source)
