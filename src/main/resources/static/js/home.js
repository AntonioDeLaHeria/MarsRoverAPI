

let userId = getUrlParameter('userId')
if (userId == null || userId == '') {
	userId = localStorage.getItem('userId')
	if(userId == null || useId == '') {
		document.getElementById('createUser').value = true;
	}
}

if (userId != null && userId != '') {
	localStorage.setItem('userId',userId)
	document.getElementById('userId').value = userId
	
}
let marsApiButtons = document.querySelectorAll("button[id*='marsApi']")
	
	
	
	// iterate through marsApiButtons and add an EventListner to them
	marsApiButtons.forEach(button => button.addEventListener('click',function() {
		// assigning whichever button we clicked to buttonId
		const buttonId = this.id
		// have 3 different type of rover(s)
		const roverButton = buttonId.split('marsApi')[1]
		// getting the <input> hidden element
		let apiData = document.getElementById('marsApiRoverData')
		apiData.value = roverButton
		// post the form and submit the form
		document.getElementById('RoverType').submit()
		
		
	}))
	
	
	
	function getUrlParameter(name) {
    name = name.replace(/[\[]/, '\\[').replace(/[\]]/, '\\]');
    var regex = new RegExp('[\\?&]' + name + '=([^&#]*)');
    var results = regex.exec(location.search);
    return results === null ? '' : decodeURIComponent(results[1].replace(/\+/g, ' '));
	};
	
	
	
	let marsRoverType = document.getElementById('marsApiRoverData').value
	
	highlightBtnByRoverType(marsRoverType)
	
	
	let marsSol = document.getElementById('marsSol').value 

	if (marsSol != null && marsSol !='' && marsSol >= 0 ) {
	  document.getElementById('marsSol').value = marsSol
	
	}
	
	
	function highlightBtnByRoverType(roverType) {
		if(roverType == '')
		roverType = 'Opportunity'
		
		document.getElementById('marsApi' + roverType).classList.remove('btn-primary')
		document.getElementById('marsApi' + roverType).classList.add('btn-secondary')

	} 
	
	
	
	
	/*
	const marsRoverType = getUrlParameter("marsApiRoverData")
	
	if(marsRoverType == 'Curiosity') {
		document.getElementById('marsApiCuriosity').classList.remove('btn-primary')
		document.getElementById('marsApiCuriosity').classList.add('btn-secondary')

	} else if (marsRoverType == 'Opportunity') {
		document.getElementById('marsApiOpportunity').classList.remove('btn-primary')
		document.getElementById('marsApiOpportunity').classList.add('btn-secondary')


	} else if (marsRoverType == 'Spirit') {
		document.getElementById('marsApiSpirit').classList.remove('btn-primary')
		document.getElementById('marsApiSpirit').classList.add('btn-secondary')


		
	} else {
		document.getElementById('marsApiOpportunity').classList.remove('btn-primary')
		document.getElementById('marsApiOpportunity').classList.add('btn-secondary')
		
	}
	*/