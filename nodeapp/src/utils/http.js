import qs from 'qs';
import router from '../router';

/**
 * Created by lucas on 2017/8/4.
 */
const codeMessage = {
  200: '服务器成功返回请求的数据。',
  201: '新建或修改数据成功。',
  202: '一个请求已经进入后台排队（异步任务）。',
  204: '删除数据成功。',
  400: '发出的请求有错误，服务器没有进行新建或修改数据的操作。',
  401: '用户没有权限（令牌、用户名、密码错误）。',
  403: '用户得到授权，但是访问是被禁止的。',
  404: '发出的请求针对的是不存在的记录，服务器没有进行操作。',
  406: '请求的格式不可得。',
  410: '请求的资源被永久删除，且不会再得到的。',
  422: '当创建一个对象时，发生一个验证错误。',
  500: '服务器发生错误，请检查服务器。',
  502: '网关错误。',
  503: '服务不可用，服务器暂时过载或维护。',
  504: '网关超时。'
};

export default function plugin(Vue, axios) {
  const VueAxios = Vue;

  if (plugin.installed) {
    return;
  }
  plugin.installed = true;
  if (!axios) {
    console.error('You have to install axios');
    return;
  }
  const checkStatus = (response) => {
    const {status, statusText} = response;
    if (status >= 200 && status < 300) {
      return response;
    }
    const errorText = codeMessage[status] || statusText;
    Vue.prototype.$message.error(errorText);
    const error = new Error(errorText);
    error.ierpStatus = response.status;
    throw error;
  };

  axios.interceptors.request.use((config) => {
    // config.headers.postCode = sessionStorage.getItem('PostCode');
    // NProgress.start()
    return config;
  });

  axios.interceptors.response.use((response) => {
    checkStatus(response);
    const data = response.data;
    // 对二进制文件进行处理。
    if (data instanceof Blob) {
      // NProgress.done()
      return data;
    }
    const {success, errCode, errMsg} = data;
    if (!success) {
      if (errCode === 'UN_LOGIN') {
        router.push({path: '/login'})
      } else {
        Vue.prototype.$message.error(errMsg === undefined || errMsg === null || errMsg === '' ? '服务器异常' : errMsg);
      }
    }
    // NProgress.done()
    return data;
  }, (error) => {
    // NProgress.done()
    if (error.response) {
      const {status, statusText} = error.response;
      const errorText = codeMessage[status] || statusText;
      Vue.prototype.$message.error(errorText);
    }
    return Promise.reject(error);
  });

  VueAxios.axios = axios;
  Object.defineProperties(VueAxios.prototype, {
    $http: {
      get() {
        return axios;
      }
    },
    $get: {
      get() {
        return (url, param) => new Promise((resolve, reject) => {
          axios.get(url, {params: param}).then((resp) => {
            if (resp.success) {
              resolve(resp.data)
            } else {
              if (url.indexOf("/context/user-info") === -1) {
                Vue.prototype.$message.error(resp.errMsg);
              }
            }
          }).catch(error => {
            reject(error)
          })
        })
      }
    },
    $post: {
      get() {
        return (url, data, useBody = false) => new Promise((resolve, reject) => {
          axios.post(url, useBody ? data : qs.stringify(data, {arrayFormat: 'brackets'})).then((resp) => {
            if (resp.success) {
              resolve(resp.data)
            } else {
              Vue.prototype.$message.error(resp.errMsg);
            }
          }).catch(error => {
            reject(error)
          })
        })
      }
    },
    $put: {
      get() {
        return (url, data, useBody = false) => new Promise((resolve, reject) => {
          axios.put(url, useBody ? data : qs.stringify(data, {arrayFormat: 'brackets'})).then((resp) => {
            if (resp.success) {
              resolve(resp.data)
            } else {
              Vue.prototype.$message.error(resp.errMsg);
            }
          }).catch(error => {
            reject(error)
          })
        })
      }
    },
    $patch: {
      get() {
        return (url, data) => new Promise((resolve, reject) => {
          axios.patch(url, qs.stringify(data, {arrayFormat: 'brackets'})).then((resp) => {
            if (resp.success) {
              resolve(resp.data)
            } else {
              Vue.prototype.$message.error(resp.errMsg);
            }
          }).catch(error => {
            reject(error)
          })
        })
      }
    },
    $delete: {
      get() {
        return (url, data) => new Promise((resolve, reject) => {
          axios.delete(url + '?' + qs.stringify(data, {arrayFormat: 'brackets'})).then((resp) => {
            if (resp.success) {
              resolve(resp.data)
            } else {
              Vue.prototype.$message.error(resp.errMsg);
            }
          }).catch(error => {
            reject(error)
          })
        })
      }
    }
  });
}
