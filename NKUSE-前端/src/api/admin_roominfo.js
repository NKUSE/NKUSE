import request from '@/utils/request'

export default{
    getAllRoom(searchModel){
        return request({
            url: '/cet/Room/getAll',
            method: 'get',
            params:{
                pageNo: searchModel.pageNo,
                pageSize: searchModel.pageSize,
            }
        }
        );
    },
    getOneRoom(searchModel){
        return request({
            url: '/cet/Room/getOne',
            method: 'get',
            params:{
                roomId: searchModel.roomId,
            }
        }
        );
    },
    getOneRoom_2(id){
        return request({
            url: '/cet/Room/getOne',
            method: 'get',
            params:{
                roomId: id,
            }
        }
        );
    },
    newRoom(room){
        return request({
            url: '/cet/Room/newRoom',
            method: 'post',
            data: room
        })
    },
    updateRoom(room){
        return request({
            url: '/cet/Room/updateRoom',
            method: 'put',
            data: room
        })
    },
    deleteRoom(id){
        return request({
            url: '/cet/Room/deleteRoom',
            method: 'delete',
            params:{
                roomId: id,
            }
        })
    }
}