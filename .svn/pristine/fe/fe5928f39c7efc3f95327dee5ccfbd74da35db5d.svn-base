/** ************************公共部分***************************** */
$(function() {
});

/** ***************************************************** */
AchieveMentUser = function() {
};

/**
 * 初始化查询
 */
AchieveMentUser.initList = function() {

	httpGet('achieveMentUserForm', 'LoadCxtPopup', BASE_PATH + '/linkAchieveChange/linkUser/getList', function() {

		pageInfo("achieveMentUserForm", function() {
            AchieveMentUser.initList();
		});

	});
};

/**
 * 查询
 * 
 */
AchieveMentUser.search = function() {
	$('#curPage').val('1');
    AchieveMentUser.initList();
};
AchieveMentUser2 = function() {
};

/**
 * 初始化查询
 */
AchieveMentUser2.initList = function() {
	
	httpGet('achieveMentUserForm', 'LoadCxtPopup', BASE_PATH + '/linkAchieveChange/linkUser/getLinkUserList2', function() {
		pageInfo("achieveMentUserForm", function() {
			AchieveMentUser2.initList();
		});
		
	});
};

/**
 * 查询
 * 
 */
AchieveMentUser2.search = function() {
	$('#curPage').val('1');
	AchieveMentUser2.initList();
};

AchieveMentUser3 = function() {
};

/**
 * 初始化查询
 */
AchieveMentUser3.initList = function() {
	
	httpGet('achieveMentUserForm', 'LoadCxtPopup', BASE_PATH + '/keFuOrder/getOperatorList', function() {
		pageInfo("achieveMentUserForm", function() {
			AchieveMentUser3.initList();
		});
		
	});
};

/**
 * 查询
 * 
 */
AchieveMentUser3.search = function() {
	$('#curPage').val('1');
	AchieveMentUser3.initList();
};
