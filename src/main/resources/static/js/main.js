(function () {
    'use strict';

    $(document).on("click", "#btnAddMedicine", function (e) {
        let num = $('input[name="item_row"]').val();
        let medicines, option;
        $.ajax({
            type: 'GET',
            url: '/api/medicines/all',
            async:false,
        }).done(function (data) {
            medicines = data.medicines;
        }).fail(function (jqXhr, json, errorThrown) {
            if (jqXhr.responseJSON.errors) {
                alert(jqXhr.responseJSON.message);
            }
        });
        console.log(medicines);
        let html =
            `<div class="row no-gutters flex-nowrap item-row" id="row${num}">
                <div class="col-auto">
                    <div class="text-center w-50px pt-3">
                        <button type="button" class="border-0 bg-transparent p-0 fs-6 delete-row text-danger" data-row="${num}"><i class="fas fa-minus-circle"></i></button>
                    </div>
                </div>
                <div class="col-3">
                    <div class="text-center border p-2 h-100">
                        <select class="form-control form-control-sm" id="items${num}.medicineId" name="items[${num}].medicineId">
                            <option value="">Vui lòng chọn thuốc</option>
                        </select>
                    </div>
                </div>
                <div class="col-2">
                    <div class="text-center border p-2 h-100 text-unit"></div>
                    <input type="hidden" name="items[${num}].unit" id="unit${num}"/>
                </div>
                <div class="col-2">
                    <div class="text-center border p-2 h-100">
                        <input type="text" class="form-control form-control-sm quantity-input" id="items${num}.quantity" name="items[${num}].quantity" placeholder=""/>
                    </div>
                </div>
                <div class="col-2">
                    <div class="text-center border p-2 h-100 text-price"></div>
                    <input type="hidden" class="unit-price-input" id="price${num}" name="items[${num}].price"/>
                </div>
                <div class="col">
                    <div class="border px-2 pt-3 py-2 h-100 text-right medicine-total" id="medicine_total${num}"></div>
                </div>
            </div>`;
        num++;
        $('input[name="item_row"]').val(num);
        $('.item-table').append(html);
    });
})();