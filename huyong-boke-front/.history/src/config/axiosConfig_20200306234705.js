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
    cancel()
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
let base = 'http://localhost:8081'
export default axiosService
export const postRequest = (url, params) => {
  return axios({
    method: 'post',
    url: `${base}${url}`,
    data: params,
    transformRequest: [function (data) {
      let ret = ''
      for (let it in data) {
        ret += encodeURIComponent(it) + '=' + encodeURIComponent(data[it]) + '&'
      }
      return ret
    }],
    headers: {
      'Content-Type': 'application/x-www-form-urlencoded'
    }
  });
}
export const uploadFileRequest = (url, params) => {
  return axios({
    method: 'post',
    url: `${base}${url}`,
    data: params,
    headers: {
      'Content-Type': 'multipart/form-data'
    }
  });
}
export const putRequest = (url, params) => {
  return axios({
    method: 'put',
    url: `${base}${url}`,
    data: params,
    transformRequest: [function (data) {
      let ret = ''
      for (let it in data) {
        ret += encodeURIComponent(it) + '=' + encodeURIComponent(data[it]) + '&'
      }
      return ret
    }],
    headers: {
      'Content-Type': 'application/x-www-form-urlencoded'
    }
  });
}
export const deleteRequest = (url) => {
  return axios({
    method: 'delete',
    url: `${base}${url}`
  });
}
export const getRequest = (url) => {
  return axios({
    method: 'get',
    url: `${base}${url}`
  });
}
