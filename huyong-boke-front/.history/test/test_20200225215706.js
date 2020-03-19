let arr1 = [1,2,3,4]
let arr2 = [1,2,3,4]
arr1.forEach(x => console.log(x))
console.log(typeof arr1)
arr1.push.apply(arr1,arr2)
console.log(arr1)
f1('hwllo world')
function f1() {
  for (const key of arguments) {
    console.log(key)
  }
  console.log(arguments[0])
}
console.log('******')
let str = 'hello world'
let test = str.slice(' ')
console.log(test)
