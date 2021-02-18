#include "main_widget.h"

MainWidget::MainWidget(QWidget *parent)
    : QWidget(parent)
{
    zthread_ = new ZcsThread();
//    connect()
    zthread_->start();
}

MainWidget::~MainWidget()
{
}

