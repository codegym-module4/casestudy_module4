(function () {
    'use strict';

    $(function() {
        $('.flash_message').fadeOut(5000);
    });

    $(document).on("click", "#btnAddMedicine", function (e) {
        let num = $('input[name="item_row"]').val();
        let option = '';
        $.ajax({
            type: 'GET',
            url: '/api/medicines',
            async:false,
        }).done(function (data) {
            let medicines = data;
            $.each(medicines, function (key, medicine) {
               option  += '<option value="'+medicine.id+'">'+medicine.name+'</option>';
            });
        }).fail(function (jqXhr, json, errorThrown) {
            if (jqXhr.responseJSON.errors) {
                alert(jqXhr.responseJSON.message);
            }
        });
        let html =
            `<div class="row no-gutters flex-nowrap item-row" data-row="${num}" id="row${num}">
                <div class="col-auto">
                    <div class="text-center w-50px pt-3">
                        <button type="button" class="border-0 bg-transparent p-0 fs-6 delete-row text-danger" data-row="${num}"><i class="fas fa-minus-circle"></i></button>
                    </div>
                </div>
                <div class="col-3">
                    <div class="text-center border p-2 h-100">
                        <select class="form-control form-control-sm medicine-select" id="items${num}.medicine" name="items[${num}].medicine">
                            <option value="">Vui lòng chọn thuốc</option>
                            ${option}
                        </select>
                    </div>
                </div>
                <div class="col-2">
                    <div class="text-center border p-2 h-100 text-unit"></div>
                    <input type="hidden" name="items[${num}].unit" id="unit${num}"/>
                </div>
                <div class="col-2">
                    <div class="text-center border p-2 h-100">
                        <input type="text" class="form-control form-control-sm quantity-input" id="quantity${num}" name="items[${num}].quantity" placeholder=""/>
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

    $(document).on("change", ".medicine-select", function (e) {
        let dataRow = $(this).closest(".item-row");
        let index = dataRow.data("row");
        let medicineId = $(this).val();
        medicineId = parseInt(medicineId);
        $.ajax({
            type: 'GET',
            url: '/api/medicines/' + medicineId,
        }).done(function (data) {
            let priceDiv = dataRow.find(".text-price");
            let unitDiv = dataRow.find(".text-unit");
            if (data != "") {
                if ($('#wholesale').length) {
                    $('#unit' + index).val(data.unit);
                    unitDiv.text(data.unit);
                    $('#quantity' + index).val(1);
                    $('#price' + index).val(data.wholesalePrice);
                    priceDiv.text(data.wholesalePrice);
                    $('#medicine_total'+index).text(data.wholesalePrice);
                } else {
                    $('#unit' + index).val(data.conversionUnit);
                    unitDiv.text(data.conversionUnit);
                    $('#quantity' + index).val(1);
                    $('#price' + index).val(data.retailPrice);
                    priceDiv.text(data.retailPrice);
                    $('#medicine_total'+index).text(data.retailPrice);
                }
            } else {
                alert("Không tìm thấy thuốc");
                $('#unit' + index).val('');
                unitDiv.text('');
                $('#quantity' + index).val('');
                $('#price' + index).val('');
                priceDiv.text('');
                $('#medicine_total'+index).text('');
            }
            calculateTotal();
        }).fail(function (jqXhr, json, errorThrown) {
            if (jqXhr.responseJSON.errors) {
                alert(jqXhr.responseJSON.message);
            }
        });
    });

    $(document).on("click", ".delete-row", function (e) {
        let index = $(this).data("row");
        let row = $("#row"+index);
        if (row.length) {
            row.remove();
        }
        let totalRow = $('input[name="item_row"]').val();
        index++;
        if (totalRow == index) {
            totalRow--;
            $('input[name="item_row"]').val(totalRow);
        }
        calculateTotal();
    });

    $(document).on("change", ".quantity-input", function (e) {
        let value = $(this).val();
        if (isNaN(value) || value < 0) {
            alert("Số lượng phải là số nguyên dương và lớn hơn 1");
            return;
        }
        let dataRow = $(this).closest(".item-row");
        let index = dataRow.data("row");
        let medicineId = dataRow.find(".medicine-select").val();
        let total = 0;
        if (medicineId != "") {
            let unitPrice = $("#price" + index).val();
            total = unitPrice * value;
            $("#medicine_total" + index).text(total);
        } else {
            alert("Vui lòng chọn thuốc trước");
        }
        calculateTotal();
    });
})();

function calculateTotal() {
    let total = 0;
    $(".item-row").each(function(index, element) {
        let $element = $(element);
        let medicineTotal = $element.find('.medicine-total').text();
        medicineTotal = parseFloat(medicineTotal);
        if (medicineTotal != "" && $.isNumeric(medicineTotal)) {
            total += medicineTotal;
        }
    });
    $('#receipt_total').val(total);
}