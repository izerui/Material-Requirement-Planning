/**
 * Created by Skye on 2017/8/5.
 */

import axios from 'axios';
import http from '@/utils/http';

const install = (Vue) => {
  Vue.use(http, axios);
};

export default install;
