const getters = {
  sidebar: state => state.app.sidebar,
  device: state => state.app.device,
  token: state => state.user.token,
  avatar: state => state.user.avatar,
  name: state => state.user.name,
  roles: state => state.user.roles,
  visitedViews: state => state.tagsView.visitedViews,
  cachedViews: state => state.tagsView.cachedViews,   
  permission_routes: state => state.permission.routes,
  userid: state => state.user.id,
  selected_onlineexam_id: state => state.selected_onlineexam_id
}
export default getters
