async function loadOrder() {
    const orderBox = document.getElementById("order-box");
    const data = localStorage.getItem("recent_order");

    if (!data) {
        orderBox.innerHTML = `
            <div class="bg-red-100 border border-red-300 text-red-700 p-4 rounded-xl text-center text-lg">
                No recent order found. Redirecting to home...
            </div>
        `;

        setTimeout(() => {
            window.location.href = "index.html";
        }, 2000);

        return;
    }

    const order = JSON.parse(data);

    orderBox.innerHTML = `
        <div class="bg-white shadow-lg rounded-xl p-6 border border-gray-200">
            <h2 class="text-2xl font-bold text-gray-800 mb-4">Order Confirmation</h2>

            <div class="mb-4">
                <p><strong>Order ID:</strong> ${order.id}</p>
                <p><strong>Location:</strong> ${order.location}</p>
                <p><strong>Total Items:</strong> ${order.productIds?.length || 0}</p>
            </div>

            <h3 class="text-xl font-semibold text-gray-700 mb-2">Products</h3>
            <ul class="space-y-2">
                ${order.productIds?.map(id =>
                    `<li class="bg-gray-100 p-3 rounded-lg border border-gray-300">Product ID: ${id}</li>`
                ).join("")}
            </ul>

            <button onclick="goHome()"
                class="mt-6 bg-blue-600 hover:bg-blue-700 text-white px-6 py-3 rounded-lg shadow">
                Return Home
            </button>
        </div>
    `;
}

function goHome() {
    window.location.href = "index.html";
}

document.addEventListener("DOMContentLoaded", loadOrder);
