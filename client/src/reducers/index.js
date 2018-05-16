
export function fake ( state = {} , action) {
	switch(action.type) {
		case 'LOGIN' : 
			return {

			};
		default : 
			return state;	
	}
}