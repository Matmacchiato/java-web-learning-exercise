/********************************************************************************
 * 表格编辑器 author: Zhijun Liu, March, 2022
 * el: 容器
 * header格式：
 * {
 *    caption: '标题',
 *    cols: [
 *       {title: '列标题', name: '列名'}],
 *       {title: '列标题', name: '列名'}],
 *       {title: '列标题', name: '列名', option=['a', 'b']}],
 *    ]
 * }
 * data: 数据格式
 * [
 *    {name1: '值1', name2: '值2'}
 * ]
 * ****************************************************************************/
function render_table(el, header, baseURL) {
    //渲染表头
    (function render_header(){
        let head1=head2=''
        header.cols.forEach(function(e){
            head1+=`<th>${e.title}</th>`
            let ctrl='<input type="text" style="width: 98%">'
            if(e.option){
                ctrl='<select style="width: 98%">'
                e.option.forEach(function(o, index){
                    ctrl+=(`<option value="${index}">${o}</option>`)
                })
                ctrl+="</select>"
            }
            head2+=`<td>${ctrl}</td>`
        })
        head1+=('<th style="width: 15%">操作</th>')
        head2+=('<td><button>添加</button><button>搜索</button></td>')
        let template=`
            <table>
              <thead>
                <tr>
                  <th colspan="${header.cols.length+1}" style="font-size: 20px">${header.caption}</th>
                </tr>
                <tr>
                  ${head1}
                </tr>
                <tr>
                  ${head2}
                </tr>
              </thead>
              <tbody>
              </tbody>
            </table>
          `
        document.querySelector(el).innerHTML=template
    })()
    // 表体对象
    let tbody=document.querySelector(`${el} table tbody`)
    // 表头控件输入行对象
    let thead_ctrls=document.querySelector(`${el} table thead`).children[2]
    let taggedIndex=-1 // 标记当前行
    let edit_status=false // 标记修改状态
    let data=null //数据模型
    axios.defaults.baseURL=baseURL

    // 获取行对象tr的数据记录
    function get_record(tr) {
        record={}
        let tds=tr.children
        for(let index=0; index<header.cols.length; index++){
            let child=tds[index].firstElementChild
            if (child.tagName==='INPUT') {
                record[header.cols[index].name]=child.value
            } else if(child.tagName==='SELECT') {
                record[header.cols[index].name]=child.selectedIndex
            }
        }
        return record
    }

    // 添加按钮响应
    document.querySelector(`${el} table thead button`).onclick=function(){
        if(edit_status) {
            alert("请先点击确定/取消")
            return
        }
        append_row(get_record(this.parentNode.parentNode))
    }
    // 搜索按钮
    document.querySelectorAll(`${el} table thead button`)[1].onclick=function(){
        if(edit_status) {
            alert("请先点击确定/取消")
            return
        }
        let child=this.parentElement.parentElement.firstElementChild.firstElementChild
        let key=null
        if(child.tagName==='INPUT'){
            key=child.value
        }else if(child.tagName==='SELECT'){
            key=child.selectedIndex
        }

        for(let index=0; index<data.length; index++){
            if(data[index][header.cols[0].name]==key){
                tagRecord(index)
                break
            }
        }
    }

    // 读取数据
    function read() {
        axios.get('/').then(r=>{
            data=r.data
            render_body()
        })
    }
    // 添加行
    function append_row(record){
        axios.post('/', record).then(r=>{
            let result=r.data
            if(result.success){
                data.push(result.data)
                render_body()
            }else{
                alert(result.message)
            }
        })
    }

    // 删除data的第index行
    function delete_row(index){
        if(edit_status) {
            alert("请先点击确定/取消")
            return
        }

        if (!confirm("真的要删除当前记录吗?")) return
        axios.delete(`/${data[index].id}`).then(r=>{
            let result=r.data
            if(result.success){
                data.splice(index, 1)
                tbody.removeChild(tbody.childNodes[index])
            }
        })
    }

    // 修改data的index行
    function update_row(index){
        if(edit_status) {
            alert("请先点击确定/取消")
            return
        }
        edit_status=true
        tagRecord(index)
        let tds=tbody.children[index].children
        let old_ctrls=[]
        for(let col=0; col<header.cols.length; col++){
            let child=tds[col].firstChild
            old_ctrls.push(child)
            if(child)tds[col].removeChild(child)
            let head_ctrl=thead_ctrls.children[col].firstElementChild
            let ctrl=document.createElement(head_ctrl.tagName)
            ctrl.setAttribute('style', head_ctrl.getAttribute('style'))
            ctrl.innerHTML=head_ctrl.innerHTML

            if(ctrl.tagName=='INPUT'){
                ctrl.value=data[index][header.cols[col].name]
            }else if(ctrl.tagName==='SELECT'){
                ctrl.selectedIndex=data[index][header.cols[col].name]
            }
            tds[col].append(ctrl)
        }

        // 老操作列，用于恢复
        let old_td=tbody.children[index].lastElementChild
        let td=document.createElement('td')
        let ok_button=document.createElement('button')
        let cancel_button=document.createElement('button')
        ok_button.innerHTML='确定'
        ok_button.style['background-color']='#be1'
        cancel_button.innerHTML='取消'
        cancel_button.style['background-color']='#eb1'

        // 点击确定按钮进行修改
        ok_button.onclick=function(){
            let record=get_record(this.parentNode.parentNode)
            axios.put(`/${data[index].id}`, record).then(r=>{
                let result=r.data
                if(result.success){
                    data[index]=result.data
                    // 修改当前行
                    for (let col=0; col<header.cols.length; col++) {
                        let ctrl=tds[col].firstElementChild
                        if (ctrl.tagName==='INPUT') {
                            tds[col].innerHTML=ctrl.value
                        } else if(ctrl.tagName==='SELECT') {
                            tds[col].innerHTML=header.cols[col].option[ctrl.selectedIndex]
                        }
                    }
                    // 恢复老操作列
                    tbody.children[index].removeChild(td)
                    tbody.children[index].append(old_td)
                    edit_status=false
                }else{
                    alert(result.message)
                }
            })
        }
        cancel_button.onclick=function(){
            // 恢复原有列内容
            old_ctrls.forEach(function(ctrl, col){
                let child=tds[col].firstChild
                tds[col].removeChild(child)
                if(ctrl)tds[col].append(ctrl)
            })
            // 恢复老操作
            tbody.children[index].removeChild(td)
            tbody.children[index].append(old_td)
            edit_status=false
        }
        td.append(ok_button)
        td.append(cancel_button)
        tbody.children[index].removeChild(old_td)
        tbody.children[index].append(td)
    }

    // 渲染表体函数
    function render_body(){
        tbody.innerHTML=''
        data.forEach(function(e, index){
            let tr=document.createElement("tr")
            header.cols.forEach(function(h){
                let item=e[h.name]
                if(h.option)item=h.option[item]
                let td=document.createElement('td')
                td.innerHTML=item
                tr.appendChild(td)
            })
            let td=document.createElement('td')
            let delete_button=document.createElement('button')
            let update_button=document.createElement('button')
            delete_button.innerHTML="删除"
            update_button.innerHTML="修改"
            delete_button.onclick=function(){
                delete_row(this.parentNode.parentNode.rowIndex-3)
            }
            update_button.onclick=function(){
                update_row(this.parentNode.parentNode.rowIndex-3)
            }
            td.append(delete_button)
            td.append(update_button)
            tr.append(td)
            tbody.appendChild(tr)
        })
    }
    // 标识当前记录
    function tagRecord(index) {
        taggedIndex=index
        if(taggedIndex<0) return
        for(let index=0; index<tbody.children.length; index++){
            if(index===taggedIndex){
                tbody.children[index].style['background-color']='#eee'
            }else{
                tbody.children[index].style['background-color']='#fff'
            }
        }
    }
    read()
}
