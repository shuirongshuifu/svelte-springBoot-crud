<script>
    import { createEventDispatcher } from "svelte";
    import "./Page.css";

    // Props - 接收整个分页信息对象
    export let pageInfo = {
        currentPage: 1,
        pageSize: 10,
        total: 0,
    };

    // 创建事件分发器
    const dispatch = createEventDispatcher();

    // 计算总页数
    $: totalPages = Math.ceil(pageInfo.total / pageInfo.pageSize);

    // 是否禁用上一页按钮
    $: isPrevDisabled = pageInfo.currentPage <= 1;

    // 是否禁用下一页按钮
    $: isNextDisabled = pageInfo.currentPage >= totalPages;

    // 统一处理翻页（offset: -1 上一页, +1 下一页）
    function changePage(offset) {
        const newPage = pageInfo.currentPage + offset;
        if (newPage >= 1 && newPage <= totalPages) {
            dispatch("pageChange", newPage);
        }
    }
</script>

<div class="pagination">
    <button
        class="btn btn-secondary"
        disabled={isPrevDisabled}
        on:click={() => changePage(-1)}
    >
        上一页
    </button>
    <span class="pagination-info">
        {#if totalPages > 0}
            第 {pageInfo.currentPage} / {totalPages} 页，共 {pageInfo.total} 条记录
        {:else}
            暂无数据
        {/if}
    </span>
    <button
        class="btn btn-secondary"
        disabled={isNextDisabled}
        on:click={() => changePage(1)}
    >
        下一页
    </button>
</div>
