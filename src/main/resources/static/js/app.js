function student_edit(e){
    let tr = document.getElementById("tr-"+e.id)
    let inputLastName = document.getElementById("txtLastName")
    let inputFirstName = document.getElementById("txtFirstName")
    let  inputEstudentId = document.getElementById("txtEstudentId")

    let estudentId = tr.children[0].innerHTML
    let lastName = tr.children[1].innerHTML
    let firstName = tr.children[2].innerHTML

    inputEstudentId.value = estudentId
    inputLastName.value = lastName
    inputFirstName.value = firstName


}

function class_edit(e){
    let tr = document.getElementById("tr-"+e.id)
    let inputClassTitle = document.getElementById("txtClassTitle")
    let inputClassDescription = document.getElementById("txtClassDescription")
    let  inputClassCode = document.getElementById("txtClassCode")

    let code = tr.children[0].innerHTML
    let title = tr.children[1].innerHTML
    let description = tr.children[2].innerHTML

    inputClassCode.value = code
    inputClassTitle.value = title
    inputClassDescription.value = description
}

function ajax_get_classes_by_student(e){
    fetch('http://localhost:8080/api/class/students?idStudent=' + e.id)
        .then(response => response.json())
        .then(data => {
            console.log(data)
            let html = generate_html_classes(data)
            document.getElementById("list-classes").innerHTML = html
        });
}

function ajax_get_students_by_class(e){
    fetch('http://localhost:8080/api/student/classes?idClass=' + e.id)
        .then(response => response.json())
        .then(data => {
            console.log(data)
            let html = generate_html_students(data)
            document.getElementById("list-students").innerHTML = html
        });
}

function generate_html_classes(data){
    let html = ''
    data.forEach(cls => {
        html += `<li class="list-group-item">${cls.classTitle}</li>`
    })
    return html
}
function generate_html_students(data){
    let html = ''
    data.forEach(std => {
        html += `<li class="list-group-item">${std.lastName} ${std.firstName}</li>`
    })
    return html
}


document.addEventListener('DOMContentLoaded', function () {
    if(window.location.href === "http://localhost:8080/"){
        ajax_get_data_for_graphic()
    }

});

function ajax_get_data_for_graphic(){
    fetch('http://localhost:8080/api/graphic/total')
        .then(response => response.json())
        .then(data => {
            console.log(data)
            graphic(data)
        });
}

function graphic(data){
    const chart = Highcharts.chart('container', {
        chart: {
            type: 'bar'
        },
        title: {
            text: 'Schoology'
        },
        xAxis: {
            categories: ['Students', 'Classes', 'Inscriptions']
        },
        yAxis: {
            title: {
                text: 'Count'
            }
        },
        series: [
            {
                name: 'Total',
                data: [data.students, data.classes, data.inscriptions]
            }
        ]
    });
}




