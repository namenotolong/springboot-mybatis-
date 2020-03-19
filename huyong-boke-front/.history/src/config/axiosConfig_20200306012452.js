import axios from 'axios';
import {Message} from 'element-ui'
const axiosService = axios.create({
  baseURL: 'http://localhost:8081'
})
axiosService.interceptors.request.use(config => {
  return config
}, err=>{
  console.log(err)
  return Promise.reject(err)
})
axiosService.interceptors.response.use(response => {
  if (!response.success) {
    Message.error({message: response.data.message})
    return response;
  }
  return response
}, err=>{
  Message.error({message: err})
  return Promise.reject(err)
})
const get = (url, data= {}, headers) => {
  return new Promise((resolve, reject) => {
    service({
      url,
      data: JSON.stringify(data),
      headers: {
        'Content-Type' : 'application/json;chart=UTF-8'
        //'Content-Type' : 'mutipart/form-data'
      },
      method: 'post'
    })
  }).then(response => {
    resolve(response.data)
  })
  .catch((error) => {
    reject(error)
  })
}
export default axiosService
