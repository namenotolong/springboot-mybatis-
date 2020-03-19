const baseUrl = 'http://localhost:8081'
const apiMethods = {
  methods: {
    get(url, data) {
      return new Promise((resolve, reject) => {
        axios.get(baseUrl + url, data).then((response) => {
          resolve(response.data)
        }, (response) => {
          reject(response)
          bus.$message({
            message: '请求超时，请检查网络',
            type: 'warning'
          })
        })
      })
    },
    post(url, data) {
      return new Promise((resolve, reject) => {
        axios.post(baseUrl + url, data).then((response) => {
          resolve(response.data)
        }).catch((response) => {
          console.log('f', response)
          resolve(response)
          bus.$message({
            message: '请求超时，请检查网络',
            type: 'warning'
          })
        })
      })
    }
  }
}

export default apiMethods
