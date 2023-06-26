import request from '@/utils/request'

export default{
    getAllRegInfo(searchModel){
        return request({
            url:'/cet/registinfo/getAll',
            method:'get',
            params:{
                pageNo: searchModel.pageNo,
                pageSize: searchModel.pageSize, 
            }
        }
        );
    },
    getOneInfo(searchModel){
       return request({
            url:'/cet/registinfo/getOne',
            method:'get',
            params:{
                registId:searchModel.registId,
            }
       });
    },
    _getOneInfo(id){
        return request({
            url:'/cet/registinfo/getOne',
            method:'get',
            params:{
                registId:id,
            }
       });
    },
    newReg(reg){
        return request({
            url: '/cet/registinfo/newReg',
            method: 'post',
            data: reg
        });
    },
    updateReg(reg){
        return request({
            url: '/cet/registinfo/updateReg',
            method: 'put',
            data: reg
        });
    },
    deleteReg(id){
        return request({
            url: '/cet/registinfo/deleteReg',
            method: 'delete',
            params:{
                regId: id,
            }
        });
    },
    
    getMaximumRegistId() {
        return request({
          url: '/cet/registinfo/maxId',
          method: 'get'
        })
      },
}