<div id="top-bar">
    <ul>
        <div class="topbar-divider"></div>
        <!-- Nav Item - User Information -->
        <li>
            <a href="#" id="dropdown-toggle" onclick="dropdown()">
                <span>${sessionScope.account.firstName} ${sessionScope.account.lastName}</span>
                <img src="${sessionScope.account.profilePictureUrl!=null?sessionScope.account.profilePictureUrl:'https://minervastrategies.com/wp-content/uploads/2016/03/default-avatar.jpg'}">
            </a>
            <!-- Dropdown - User Information -->
            <div id="dropdown-menu">
                <a class="dropdown-item" href="#">
                    <i class="fa-solid fa-address-card"></i>
                    Profile
                </a>
                <a class="dropdown-item" href="#">
                    <i class="fa-solid fa-gear"></i>
                    Settings
                </a>
                <a class="dropdown-item" href="#">
                    <i class="fa-solid fa-list-check"></i>
                    Activity Log
                </a>
                <div class="dropdown-divider"></div>
                <a class="dropdown-item" href="../logout">
                    <i class="fa-solid fa-right-from-bracket"></i>
                    Logout
                </a>
            </div>
        </li>
    </ul>
</div>