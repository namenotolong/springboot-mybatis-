import AxiosConfig from './axiosConfig' // 这里使用axios封装了http请求
import {baseURL,getStore} from '../utils/utils'

/* 富文本编辑图片上传配置 */
const uploadConfig = {
  name: 'file', // 必填参数 文件的参数名
  accept: 'image/png, image/gif, image/jpeg, image/bmp, image/x-icon' // 可选 可上传的图片格式
}

// toolbar工具栏的工具选项（默认展示全部）
const toolOptions = [
  ['bold', 'italic', 'underline', 'strike'],
  ['blockquote', 'code-block'],
  [{ 'header': 1 }, { 'header': 2 }],
  [{ 'list': 'ordered' }, { 'list': 'bullet' }],
  [{ 'script': 'sub' }, { 'script': 'super' }],
  [{ 'indent': '-1' }, { 'indent': '+1' }],
  [{ 'direction': 'rtl' }],
  [{ 'size': ['small', false, 'large', 'huge'] }],
  [{ 'header': [1, 2, 3, 4, 5, 6, false] }],
  [{ 'color': [] }, { 'background': [] }],
  [{ 'font': [] }],
  [{ 'align': [] }],
  ['clean'],
  ['link', 'image']
]
const handlers = {
  
}

export default {
  theme: 'snow', // 主题
  modules: {
    toolbar: {
      container: toolOptions, // 工具栏选项
      handlers: handlers // 事件重写
    }
  },
  placeholder: '从这儿开始'
}
