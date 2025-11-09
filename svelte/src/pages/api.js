// API服务文件，用于调用后端接口
const API_BASE_URL = 'http://localhost:8080'; // 后端服务器地址

class ApiService {
  constructor() {
    this.baseURL = API_BASE_URL;
  }

  // 通用请求方法
  async request(endpoint, options = {}) {
    const url = `${this.baseURL}${endpoint}`;
    const config = {
      headers: {
        'Content-Type': 'application/json',
        ...options.headers,
      },
      ...options,
    };

    try {
      const response = await fetch(url, config);
      const data = await response.json();

      if (!response.ok) {
        throw new Error(data.message || `HTTP error! status: ${response.status}`);
      }

      return data;
    } catch (error) {
      console.error('API request failed:', error);
      throw error;
    }
  }

  // 获取人员列表（分页）
  getPeoplePage(params) {
    const queryString = new URLSearchParams(params).toString();
    return this.request(`/people/page?${queryString}`);
  }

  // 根据ID获取人员详情
  getPeopleById(id) {
    return this.request(`/people/${id}`);
  }

  // 创建人员
  createPeople(peopleData) {
    return this.request('/people', {
      method: 'POST',
      body: JSON.stringify(peopleData),
    });
  }

  // 更新人员
  updatePeople(peopleData) {
    return this.request('/people', {
      method: 'PUT',
      body: JSON.stringify(peopleData),
    });
  }

  // 删除人员
  deletePeople(ids) {
    const queryString = new URLSearchParams({ ids: ids.join(',') }).toString();
    return this.request(`/people?${queryString}`, {
      method: 'DELETE',
    });
  }
}

// 导出单例实例
export const apiService = new ApiService();
