<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

return new class extends Migration
{
    /**
     * Run the migrations.
     */
    public function up(): void
    {
        Schema::create('partner', function (Blueprint $table) {
            $table->unsignedBigInteger('id')->primary()->comment('고유키');
            $table->string('company_name', 30)->comment('사업자 명');
            $table->string('company_registration_no', 10)->comment('사업자 번호');
            $table->string('company_address', 150)->comment('사무실 주소');
            $table->string('ceo_name', 20)->comment('대표자 명');
            $table->string('ceo_mobile_no', 20)->comment('대표자 휴대전화 번호');
            $table->dateTime('ceo_mobile_no_verified_at')->comment('대표자 휴대전화 번호 인증 일시');
            $table->dateTime('created_at')->index()->comment('생성일시');
        });
    }

    /**
     * Reverse the migrations.
     */
    public function down(): void
    {
        Schema::dropIfExists('partner');
    }
};
