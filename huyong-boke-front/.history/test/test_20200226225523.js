function* helloWorldGenerator() {
  yield 'hello';
  yield 'world';
  yield* t1()
  return 'ending';
}
function* t1() {
  yield 'hello1';
  yield 'world1';
  return 'ending1';
}
var hw = helloWorldGenerator();
console.log(hw.next())
console.log(hw.next())
console.log(hw.next())
console.log(hw.next())
