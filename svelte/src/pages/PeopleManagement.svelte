<script>
  import { onMount } from "svelte";
  import { apiService } from "./api.js";
  import "./PeopleManagement.css";
  import Page from "./components/Page.svelte";

  const getEmptyPerson = () => ({
    id: null,
    name: "",
    age: "",
    home: "",
    remark: "",
  });

  const getEmptySearch = () => ({
    name: "",
    age: "",
    home: "",
    remark: "",
  });

  // 数据状态
  let people = [];
  let loading = false;

  // 分页参数对象
  let pageInfo = {
    currentPage: 1,
    pageSize: 2,
    total: 0,
  };

  // 搜索参数
  let searchParams = getEmptySearch();

  // 对话框状态
  let showDialog = false;
  let isEditing = false;
  let currentPerson = getEmptyPerson();

  // 批量选择
  let selectedIds = new Set();

  // 计算属性：全选状态
  $: isAllSelected = people.length > 0 && selectedIds.size === people.length;

  // 统一的字段配置
  const fieldConfigs = [
    {
      key: "name",
      label: "姓名",
      type: "text",
      placeholder: "请输入姓名",
      required: true,
    },
    {
      key: "age",
      label: "年龄",
      type: "number",
      placeholder: "请输入年龄",
      required: true,
    },
    {
      key: "home",
      label: "住址",
      type: "text",
      placeholder: "请输入住址",
      required: false,
    },
    {
      key: "remark",
      label: "备注",
      type: "text",
      placeholder: "请输入备注",
      required: false,
    },
  ];

  // 辅助函数：更新选择状态
  function updateSelectedIds(fn) {
    fn(selectedIds);
    selectedIds = new Set(selectedIds);
  }

  // 加载人员数据
  async function loadPeople() {
    loading = true;
    try {
      const params = {
        ...searchParams,
        currentPage: pageInfo.currentPage,
        pageSize: pageInfo.pageSize,
      };
      const response = await apiService.getPeoplePage(params);
      people = response.data.records || [];
      pageInfo.total = response.data.total || 0;
    } catch (error) {
      console.error("加载人员数据失败:", error);
    } finally {
      loading = false;
    }
  }

  // 搜索
  function handleSearch() {
    pageInfo.currentPage = 1;
    loadPeople();
  }

  // 重置搜索
  function handleReset() {
    searchParams = getEmptySearch();
    pageInfo.currentPage = 1;
    loadPeople();
  }

  // 打开新增对话框
  function openCreateDialog() {
    isEditing = false;
    currentPerson = getEmptyPerson();
    showDialog = true;
  }

  // 打开编辑对话框
  function openEditDialog(person) {
    isEditing = true;
    currentPerson = { ...person };
    showDialog = true;
  }

  // 保存人员（新增或更新）
  async function savePerson() {
    try {
      if (isEditing) {
        const res = await apiService.updatePeople(currentPerson);
        alert("更新成功");
        pageInfo.currentPage = 1;
      } else {
        const res = await apiService.createPeople(currentPerson);
        if (res.code === 200) {
          alert("创建成功");
        } else {
          alert(res.message);
        }
        pageInfo.currentPage = 1;
      }
      showDialog = false;
      loadPeople();
    } catch (error) {
      console.error("保存失败:", error);
    }
  }

  // 删除数据
  async function deleteData(ids) {
    const idsArray = [ids].flat();

    if (
      !confirm(`确定要删除选中的 ${idsArray.length} 条记录吗？删除后无法恢复！`)
    ) {
      console.log("取消删除");
      return;
    }

    try {
      await apiService.deletePeople(idsArray);
      alert("删除成功");
      selectedIds.clear();
      loadPeople();
    } catch (error) {
      console.error("删除失败:", error);
      alert("删除失败: " + error.message);
    }
  }

  // 分页
  function handlePageChange(event) {
    pageInfo.currentPage = event.detail;
    selectedIds.clear(); // 切换页面时清空选择
    loadPeople();
  }

  // 切换全选
  function toggleSelectAll() {
    updateSelectedIds((ids) => {
      ids.clear();
      if (!isAllSelected) {
        people.forEach((person) => ids.add(person.id));
      }
    });
  }

  // 切换单个选择
  function toggleSelect(id) {
    updateSelectedIds((ids) => {
      ids.has(id) ? ids.delete(id) : ids.add(id);
    });
  }

  // 批量删除
  function handleBatchDelete() {
    if (selectedIds.size === 0) {
      alert("请先选择要删除的记录");
      return;
    }
    deleteData(Array.from(selectedIds));
  }

  // 清空选择
  function clearSelection() {
    updateSelectedIds((ids) => ids.clear());
  }

  // 组件挂载时加载数据
  onMount(() => {
    loadPeople();
  });
</script>

<div class="app-container">
  <div class="container">
    <!-- 搜索区域 -->
    <div class="card search-card">
      <div class="search-form">
        {#each fieldConfigs as field}
          <div class="form-group">
            <label for="search-{field.key}">{field.label}</label>
            <input
              id="search-{field.key}"
              type={field.type}
              bind:value={searchParams[field.key]}
              placeholder={field.placeholder}
            />
          </div>
        {/each}
        <div class="button-group">
          <button class="btn btn-primary" on:click={handleSearch}>搜索</button>
          <button class="btn btn-secondary" on:click={handleReset}>重置</button>
        </div>
      </div>
    </div>

    <!-- 操作按钮 -->
    <div class="actions">
      <div class="actions-left">
        {#if selectedIds.size > 0}
          <button class="btn btn-danger" on:click={handleBatchDelete}>
            批量删除 ({selectedIds.size})
          </button>
          <button class="btn btn-secondary" on:click={clearSelection}>
            清空选择
          </button>
        {/if}
      </div>
      <!-- 分页组件 -->
      <div class="actions-center">
        <Page {pageInfo} on:pageChange={handlePageChange} />
      </div>
      <div class="actions-right">
        <button class="btn btn-primary" on:click={openCreateDialog}>
          + 新增人员
        </button>
      </div>
    </div>

    <!-- 数据表格 -->
    <div class="card data-card">
      {#if loading}
        <div class="loading">加载中...</div>
      {:else}
        <div class="table-container">
          <table class="table">
            <thead>
              <tr>
                <th style="width: 50px;">
                  <input
                    type="checkbox"
                    checked={isAllSelected}
                    on:change={toggleSelectAll}
                  />
                </th>
                <th>ID</th>
                <th>姓名</th>
                <th>年龄</th>
                <th>住址</th>
                <th>备注</th>
                <th>创建时间</th>
                <th style="width: 120px;">操作</th>
              </tr>
            </thead>
            <tbody>
              {#each people as person (person.id)}
                <tr>
                  <td>
                    <input
                      type="checkbox"
                      checked={selectedIds.has(person.id)}
                      on:change={() => toggleSelect(person.id)}
                    />
                  </td>
                  <td>{person.id}</td>
                  <td>{person.name}</td>
                  <td>{person.age}</td>
                  <td>{person.home || "-"}</td>
                  <td>{person.remark || "-"}</td>
                  <td>{new Date(person.createTime).toLocaleString()}</td>
                  <td>
                    <button
                      class="btn-icon"
                      on:click={() => openEditDialog(person)}
                      title="编辑"
                    >
                      ✏️
                    </button>
                    <button
                      class="btn-icon"
                      on:click={() => deleteData(person.id)}
                      title="删除"
                    >
                      🗑️
                    </button>
                  </td>
                </tr>
              {/each}
            </tbody>
          </table>

          {#if people.length === 0}
            <div class="no-data">暂无数据</div>
          {/if}
        </div>
      {/if}
    </div>
  </div>
</div>

<!-- 新增/编辑对话框 -->
{#if showDialog}
  <!-- svelte-ignore a11y_click_events_have_key_events -->
  <!-- svelte-ignore a11y_no_static_element_interactions -->
  <div class="modal-overlay" on:click={() => (showDialog = false)}>
    <div class="modal" on:click|stopPropagation>
      <div class="modal-header">
        <h2>{isEditing ? "编辑人员" : "新增人员"}</h2>
        <button class="modal-close" on:click={() => (showDialog = false)}
          >×</button
        >
      </div>
      <div class="modal-body">
        {#each fieldConfigs as field}
          <div class="form-group">
            <label for="person-{field.key}">
              {field.label}{field.required ? " *" : ""}
            </label>
            <input
              id="person-{field.key}"
              type={field.type}
              bind:value={currentPerson[field.key]}
              placeholder={field.placeholder}
              required={field.required}
            />
          </div>
        {/each}
      </div>
      <div class="modal-footer">
        <button class="btn btn-secondary" on:click={() => (showDialog = false)}
          >取消</button
        >
        <button class="btn btn-primary" on:click={savePerson}>保存</button>
      </div>
    </div>
  </div>
{/if}
